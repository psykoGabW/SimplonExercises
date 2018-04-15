package co.simplon.gwi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class TestSQLTransactions {

	/**
	 * This method load properties file linked to this class.
	 * 
	 * @return Properties
	 */
	private static Properties getProperties() {

		Properties loadedProperties = new Properties();
		String propertiesFileName = TestSQLTransactions.class.getSimpleName() + ".properties";
		System.out.println("Load " + propertiesFileName + "...");
		try (FileInputStream fIS = new FileInputStream(propertiesFileName)) {
			loadedProperties.load(fIS);
		} catch (FileNotFoundException e) {
			System.out.println(propertiesFileName + " is missing.");
		} catch (IOException e) {
			System.out.println("Errors occured during " + propertiesFileName + " reading");
			loadedProperties = null;
		}

		return loadedProperties;
	}

	/**
	 * Lazy function so that we don't have to repeat for each updating query try /
	 * catch
	 * 
	 * @param conn
	 *            Connection to use for query execution
	 * @param preparedQuery
	 *            String containing query to execute
	 * @param param
	 *            List of Object to use in prepared query
	 * @return boolean to indicate if query execution is correct or not.
	 */
	private static boolean executeUpdateQuery(Connection conn, String preparedQuery, List<Object> param) {

		try (PreparedStatement pS = conn.prepareStatement(
				preparedQuery)) {

			for (int i = 0; i < param.size(); i++) {
				pS.setObject(i + 1, param.get(i));
			}
			pS.execute();

			return true;
		} catch (SQLException e) {
			System.out.println("Error on executeUpdateQuery - " + preparedQuery + System.lineSeparator() + "SQLException : " + e.getMessage());
			// e.printStackTrace();
			return false;
		}
	}

	/**
	 * Lazy method to rollbackInstructions
	 * 
	 * @param conn
	 *            Connection to use for roll back
	 * @return boolean to indicate if roll back is correctly done or not
	 */
	private static boolean rollbackInstructions(Connection conn) {
		try {
			conn.rollback();
			return true;
		} catch (SQLException e) {
			System.out.println("Critical ERROR - rollbackInstructions - Rollback failed ! ");
			return false;
		}
	}

	/**
	 * Lazy method to commit SQL instruction done until this call
	 * 
	 * @param conn
	 *            Connection to use for commit
	 * @return Boolean to indicate if commit is correctly done or not
	 */
	private static boolean commitInstructions(Connection conn) {
		try {
			conn.commit();
			return true;
		} catch (SQLException e) {
			rollbackInstructions(conn);
			return false;
		}

	}

	/**
	 * This function is used to check if database integrity is OK. Used of stored
	 * procedure checkIntegrity is done.
	 * 
	 * @param conn
	 *            Connection to use to call stored procedure.
	 * @return Boolean
	 */
	private static Boolean checkDataBaseIntegrity(Connection conn) {

		Boolean integrityResult = null;

		try (CallableStatement cs = conn.prepareCall("{? = call checkIntegrity()}")) {
			cs.registerOutParameter(1, Types.BOOLEAN);
			cs.execute();

			integrityResult = (Boolean) cs.getObject(1);

		} catch (SQLException e) {
			System.out.println("checkDataBaseIntegrity failed ! ");
		}

		return integrityResult;
	}

	public static void sell(Connection conn, int clientId, int productId, int quantity) {

		boolean queriesExecution = true;

		ArrayList<Object> queryArg = new ArrayList<Object>();
		queryArg.add(0, quantity);
		queryArg.add(1, productId);
		queriesExecution = executeUpdateQuery(conn,
				"UPDATE customer SET credit= credit - ? * (SELECT price FROM product WHERE pk_id = ?);", queryArg);

		if (queriesExecution) {
			queriesExecution = executeUpdateQuery(conn, "UPDATE product SET qty= qty - ? WHERE pk_id= ?;", queryArg);
		}

		if (queriesExecution) {
			queriesExecution = executeUpdateQuery(conn, "UPDATE sales_log SET qty= qty + ? WHERE fk_product_id= ?;",
					queryArg);
		}

		if (!queriesExecution) {
			System.out.println("sell - Rollback ! ");
			rollbackInstructions(conn);
		} else {
			commitInstructions(conn);
		}
	}

	public static void credit(Connection conn, int clientId, int cash) {

		boolean queriesExecution = true;

		ArrayList<Object> queryArg = new ArrayList<Object>();
		queryArg.add(0, cash);
		queryArg.add(1, clientId);
		queriesExecution = executeUpdateQuery(conn, "UPDATE customer SET credit= credit + ? WHERE pk_id= ?;", queryArg);

		if (queriesExecution) {
			queriesExecution = executeUpdateQuery(conn, "UPDATE cash SET amount= amount + ? WHERE pk_id= ?;", queryArg);
		}

		if (!queriesExecution) {
			System.out.println("credit - Rollback ! ");
			rollbackInstructions(conn);
		} else {
			commitInstructions(conn);
		}
	}

	public static void addToInventory(Connection conn, int productId, int quantity) {
		/*
		 * UPDATE product SET qty= qty + 5 WHERE pk_id= 1;
		 */

		boolean queriesExecution = true;

		ArrayList<Object> queryArg = new ArrayList<Object>();
		queryArg.add(0, quantity);
		queryArg.add(1, productId);
		queriesExecution = executeUpdateQuery(conn, "UPDATE product SET qty= qty + ? WHERE pk_id= ?;", queryArg);

		if (!queriesExecution) {
			System.out.println("addToInventory - Rollback ! ");
			rollbackInstructions(conn);
		} else {
			commitInstructions(conn);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties properties = getProperties();

		String jdbcUrl = properties.getProperty("connectionURL");
		String username = properties.getProperty("connectionUser");
		String password = properties.getProperty("connectionPwd");

		int clientId = 1;
		int productId = 1;

		try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
			conn.setAutoCommit(false);
			Random rng = new Random();
			int count = 0;
			while (true) {
				count++;

				credit(conn, clientId, rng.nextInt(100) + 1);
				addToInventory(conn, productId, rng.nextInt(10) + 1);
				sell(conn, clientId, productId, rng.nextInt(10) + 1);

				if (count % 10 == 0) {
					count = 0;
					Boolean checkIntegrity = checkDataBaseIntegrity(conn);
					if (checkIntegrity != null) {
						System.out.println("Database integrity : " + (checkDataBaseIntegrity(conn) ? "OK :)" : "KO!"));
					}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

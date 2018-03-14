package co.simplon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DBConnexion {

	private static Scanner scanner = new Scanner(System.in);

	/**
	 * This static method execute an sql query given in parameters through
	 * Connection object passed in parameter. A display of the result is printed on
	 * standard output.
	 * 
	 * @param conn
	 *            sql connection to use
	 * @param sqlQuery
	 *            string containing sql query to execute
	 * @throws SQLException
	 */
	private static void displayQueryResult(Connection conn, String sqlQuery) throws SQLException {
		if (conn == null || conn.isClosed()) {
			System.out.println("Connection to database is closed");
			return;
		}

		try (Statement sqlStatement = conn.createStatement()) {
			System.out.println("Execution of query : " + sqlQuery);
			try (ResultSet sqlResult = sqlStatement.executeQuery(sqlQuery)) {

				System.out.println();
				System.out.println("Results ...");

				ResultSetMetaData rsmd = sqlResult.getMetaData();
				ArrayList<Integer> maxLengths = new ArrayList<Integer>();
				ArrayList<String> columnNames = new ArrayList<String>();

				for (int c = 1; c <= rsmd.getColumnCount(); c++) {
					maxLengths.add(maxLengths.size(), rsmd.getColumnName(c).length());
					columnNames.add(columnNames.size(), rsmd.getColumnName(c));
				}

				ArrayList<String[]> dataResult = new ArrayList<String[]>();

				while (sqlResult.next()) {
					String[] dataRow = new String[columnNames.size()];

					for (int c = 1; c <= rsmd.getColumnCount(); c++) {
						if (sqlResult.getString(c).length() > maxLengths.get(c - 1)) {
							maxLengths.set(c - 1, new Integer(sqlResult.getString(c).length()));
						}
						dataRow[c - 1] = sqlResult.getString(c);
					}
					dataResult.add(dataResult.size(), dataRow);
				}

				System.out.println();

				// Display
				for (int c = 0; c < columnNames.size(); c++) {

					System.out.print(getFiller(' ', maxLengths.get(c) - columnNames.get(c).length())
							+ columnNames.get(c)
							+ ((c < columnNames.size() - 1 ? " | " : "")));
				}
				System.out.println();
				for (int r = 0; r < dataResult.size(); r++) {
					for (int c = 0; c < columnNames.size(); c++) {
						System.out.print(getFiller(' ', maxLengths.get(c) - dataResult.get(r)[c].length())
								+ dataResult.get(r)[c]
								+ ((c < columnNames.size() - 1 ? " | " : "")));
					}
					System.out.println();
				}
				System.out.println();

			}
		}
	}

	private static String getFiller(char c, int nbChar) {
		String filler = "";
		if (nbChar > 0) {
			char[] f = new char[nbChar];
			Arrays.fill(f, ' ');

			filler += new String(f);
		}
		return filler;
	}

	/**
	 * 
	 * @param minAcceptedValue
	 *            (int >= 0)
	 * @param maxAcceptedValue
	 *            (int >= 0)
	 * @param isEmptyAccepted
	 *            (boolean) if true, user can input nothing and we return -1;
	 * @return -1 if isEmptyAccepted && user input "" int between minAcceptedValue
	 *         and maxAcceptedValue
	 */
	private static int getUserChoice(int minAcceptedValue, int maxAcceptedValue, boolean isEmptyAccepted) {
		int userChoice = -1;
		String literalChoice = "";
		// get User choice
		do {
			try {
				System.out.println("Your choice : ");
				literalChoice = scanner.nextLine();
				userChoice = Integer.parseInt(literalChoice);
			} catch (NumberFormatException n) {
				userChoice = -1;
			} finally {
				if ((!(userChoice >= minAcceptedValue && userChoice <= maxAcceptedValue))
						|| (literalChoice.isEmpty() && !isEmptyAccepted)) {
					System.out.println(
							"Please enter a number in [ " + minAcceptedValue + " ; " + maxAcceptedValue + " ]");
				}
			}
		} while (userChoice == -1 || (isEmptyAccepted && literalChoice.isEmpty()));

		if (literalChoice.isEmpty() && isEmptyAccepted) {
			userChoice = -1;
		}
		return userChoice;
	}

	private static void saveCity(Connection conn, CityDBO city) throws SQLException {

		if (conn == null || conn.isClosed()) {
			return;
		}

		try (Statement insertStatement = conn.createStatement()) {
			System.out.println("Line added : "
					+ insertStatement.executeUpdate(city.getInsertQuery(), Statement.RETURN_GENERATED_KEYS));
			ResultSet generatedKeys = insertStatement.getGeneratedKeys();
			// Only one key expected
			generatedKeys.next();
			city.setId(generatedKeys.getLong(1));
		}
	}

	private static void updateCity(Connection conn, CityDBO city) throws SQLException {
		if (conn == null || conn.isClosed()) {
			return;
		}

		try (Statement updateStatement = conn.createStatement()) {
			System.out.println("Line updated : "
					+ updateStatement.executeUpdate(city.getUpdateQuery()));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * try {
		 * 
		 * Class.forName("org.postgresql.Driver"); } catch (ClassNotFoundException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		// TODO : Get data from external file
		String connectionURL = "jdbc:postgresql://horton.elephantsql.com:5432/xkylujru";
		String connectionUser = "xkylujru";
		String connectionPwd = "Cf2FLh56k0z7in5lACpZnPoMp92qxn9G";

		// try with resources : automatically close object initialize inside ()
		try (Connection conn = DriverManager.getConnection(connectionURL,
				connectionUser,
				connectionPwd)) {

			System.out.println("Connection established !");

			int userChoice = -1;

			CityDBO newCity = new CityDBO("Nouvelle ville", "FR", 10.0, -10.0);

			while (userChoice != 0) {
				System.out.println();
				System.out.println("*************************");
				System.out.println("*  Main data base menu  *");
				System.out.println("*************************");
				System.out.println("1 -> Execute select query");
				System.out.println("2 -> Create new city");
				System.out.println("3 -> Update existing city");
				System.out.println();
				System.out.println("0 -> Exit program");

				userChoice = getUserChoice(0, 3, false);

				switch (userChoice) {
				case 1:
					String selectQuery = "";
					while (selectQuery.equals("")) {
						System.out.println("");
						System.out.println("Please input your select query to execute :");
						selectQuery = scanner.nextLine();
					}

					displayQueryResult(conn, selectQuery);

					break;

				case 2:
					System.out.println(newCity.getInsertQuery());
					saveCity(conn, newCity);
					break;

				case 3:
					if (newCity.hasBeenSaved()) {
						newCity.setName("updated city");
						updateCity(conn, newCity);
					} else {
						System.out.println("No Id... City can't be updated");
					}
					break;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		scanner.close();

	}

}

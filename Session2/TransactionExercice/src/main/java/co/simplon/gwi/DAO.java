package co.simplon.gwi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {

	private Connection connect; // = ConnectionPostgreSQL.getInstance();
	
	public abstract List<T> getAll();
	
	public abstract T create(T object);
	
	public abstract T read(int id);
	
	public abstract T update(T object);
	
	public abstract void delete(T object);
	
	public DAO(Connection conn) {
		// On peut gérer à ce niveau le fait que la référence de connection doit être ouverte etc...
		this.connect = conn;
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
	protected boolean executeUpdateQuery(String preparedQuery, Object... param) {

		try (PreparedStatement pS = connect.prepareStatement(
				preparedQuery)) {

			for (int i = 0; i < param.length; i++) {
				pS.setObject(i + 1, param[i]);
			}
			pS.execute();

			return true;
		} catch (SQLException e) {
			System.out.println("Error on executeUpdateQuery - " + preparedQuery + System.lineSeparator() + "SQLException : " + e.getMessage());
			// e.printStackTrace();
			return false;
		}

	}
	
}

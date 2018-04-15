package co.simplon.gwi.server_exercice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class DataBaseCounterServlet extends GenericServlet {

	private final static long serialVersionUID = 1L;
	private Properties conProperties;
	private Connection connect;

	private final static String PROPERTIES_KEY_CONNECTION_URL = "connectionURL";
	private final static String PROPERTIES_KEY_CONNECTION_USER = "connectionUser";
	private final static String PROPERTIES_KEY_CONNECTION_PWD = "connectionPwd";
	private final static String PROPERTIES_KEY_CONNECTION_SCHEMA = "connectionSchema";

	public DataBaseCounterServlet() throws FileNotFoundException, IOException, SQLException {
		super();
		
		initConnection();
		
	}

	private void initConnection() throws FileNotFoundException, IOException, SQLException {
		try {
			conProperties = loadProperties("DBConnection.properties");

			connect = DriverManager.getConnection(
					conProperties.getProperty(PROPERTIES_KEY_CONNECTION_URL),
					conProperties.getProperty(PROPERTIES_KEY_CONNECTION_USER),
					conProperties.getProperty(PROPERTIES_KEY_CONNECTION_PWD));

			connect.setSchema(conProperties.getProperty(PROPERTIES_KEY_CONNECTION_SCHEMA));

		} catch (FileNotFoundException f) {
			System.err.println("DBConnection.properties not found !");
			throw f;
		} catch (IOException i) {
			System.err.println("IOException on DBConnection.properties");
			throw i;
		} catch (SQLException e) {
			System.err.println("SQLException on DB");
			throw e;
		}

	}
	
	private Properties loadProperties(String filename) throws IOException, FileNotFoundException {

		Properties properties = new Properties();
		FileInputStream input = new FileInputStream(filename);
		try {
			properties.load(input);
			return properties;

		} finally {
			input.close();
		}
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = connect.prepareStatement("INSERT INTO threaddb.requests VALUES (?);");
			ps.setString(1, request.toString());
			ps.execute();
			ps.close();

			Statement sqlStatement = connect.createStatement();
			ResultSet result = sqlStatement.executeQuery("SELECT count(*) from threaddb.requests");

			if (result.next()) {
				response.getWriter().println("Number of rows in requests table : " + result.getDouble(1));
			}
			sqlStatement.close();
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void destroy() {
		try{connect.close();}
		catch(SQLException e) {
			System.err.println("Error on connection.close()");
		}
		
	}
	
}

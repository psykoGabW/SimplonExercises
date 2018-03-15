package co.simplon;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;

public class DBConnection {

	private static Scanner scanner = new Scanner(System.in);
	private static final String SELECT_QUERY_ALL_CITIES = "SELECT * FROM city;";

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
	private static void displaySelectQueryResult(Connection conn, String sqlQuery) throws SQLException {
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
				String underline = "";
				for (int c = 0; c < columnNames.size(); c++) {
					String columnDelimiter = (c < columnNames.size() - 1 ? " | " : "");
					System.out.print(getFiller(' ', maxLengths.get(c) - columnNames.get(c).length())
							+ columnNames.get(c)
							+ columnDelimiter);
					underline += getFiller('-', maxLengths.get(c)) + columnDelimiter;
				}
				System.out.println();
				System.out.println(underline);

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

	/**
	 * Repeat nbChar times character c.
	 * 
	 * @param c
	 *            Character to repeat
	 * @param nbChar
	 *            Number of occurrences
	 * @return String containing c character nbChar
	 */
	private static String getFiller(char c, int nbChar) {
		String filler = "";
		if (nbChar > 0) {
			char[] f = new char[nbChar];
			Arrays.fill(f, c);

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

		try (PreparedStatement updateStatement = conn
				.prepareStatement("UPDATE City SET name=?, countrycode=?, latitude=?, longitude=? WHERE id=?")) {
			updateStatement.setString(1, city.getName());
			updateStatement.setString(2, city.getCountryCode());
			updateStatement.setDouble(3, city.getLatitude());
			updateStatement.setDouble(4, city.getLongitude());
			updateStatement.setLong(5, city.getId());

			System.out.println("Line updated : "
					+ updateStatement.executeUpdate());
		} catch (SQLException e) {
			System.out.println("Error during SQL Update : " + e.getMessage());
		}
	}

	public static Properties load(String filename) throws IOException, FileNotFoundException {

		Properties properties = new Properties();
		FileInputStream input = new FileInputStream(filename);
		try {
			properties.load(input);
			return properties;

		} finally {
			input.close();
		}
	}

	private static CityDBO getCityFromDataBase(Connection conn, long cityID) {
		CityDBO city = null;
		try (PreparedStatement pS = conn
				.prepareStatement("Select name, countrycode, latitude, longitude from city where id= ?")) {
			pS.setLong(1, cityID);
			ResultSet rS = pS.executeQuery();
			while (rS.next()) {
				city = new CityDBO(
						rS.getString("name"),
						rS.getString("countryCode"),
						rS.getDouble("latitude"),
						rS.getDouble("longitude"));
				city.setId(cityID);
			}
		} catch (SQLException e) {
			System.out.println("SQL error during getCityFromDataBase method : " + e.getMessage());
		}
		return city;
	}

	private static void deleteCity(Connection conn, long cityID) {
		try(PreparedStatement pS = conn.prepareStatement("DELETE FROM City WHERE id = ?")){
			pS.setLong(1, cityID);
			System.out.println("Number of line deleted : " + pS.executeUpdate());
		}catch (SQLException e){
			System.out.println("SQL error during city deletion for id " + cityID + " : " + e.getMessage());
		}
	}
	
	private static CityDBO getCityData(CityDBO existingCity) {

		System.out.println();
		System.out.println("Input city data");

		System.out.print("City name " + (existingCity == null ? "" : "( " + existingCity.getName() + ")") + " : ");
		String cityName = scanner.nextLine();
		if (cityName.isEmpty() && existingCity != null) {
			cityName = existingCity.getName();
		}

		System.out.print(
				"Country code " + (existingCity == null ? "" : "( " + existingCity.getCountryCode() + ")") + " : ");
		String cityCountrCode = scanner.nextLine();
		if (cityCountrCode.isEmpty() && existingCity != null) {
			cityCountrCode = existingCity.getCountryCode();
		}

		System.out.print(
				"City latitude " + (existingCity == null ? "" : "( " + existingCity.getLatitude() + ")") + " : ");
		String latInput = scanner.nextLine();
		Double cityLatitude = null;

		try {
			cityLatitude = Double.parseDouble(latInput);
		} catch (NumberFormatException n) {
			if (existingCity == null) {
				System.out.println("Latitude is not a correctly formated... Latitude is set to 0 ");
				cityLatitude = 0.;
			} else {
				System.out.println("Latitude is not a correctly formated... Old latitude value will be used ");
				cityLatitude = existingCity.getLatitude();
			}
		}

		System.out.print(
				"City longitude " + (existingCity == null ? "" : "( " + existingCity.getLongitude() + ")") + " : ");
		String longitudeInput = scanner.nextLine();
		Double cityLongitude = null;

		try {
			cityLongitude = Double.parseDouble(longitudeInput);
		} catch (NumberFormatException n) {
			if (existingCity == null) {
				System.out.println("Longitude is not a correctly formated... Longitude is set to 0 ");
				cityLongitude = 0.;
			} else {
				System.out.println("Longitude is not a correctly formated... Old longitude value will be used ");
				cityLongitude = existingCity.getLongitude();
			}
		}

		CityDBO city = new CityDBO(cityName, cityCountrCode, cityLatitude, cityLongitude);
		if (existingCity != null) {
			city.setId(existingCity.getId());
		}

		return city;

	}

	private static long getCityIdFromConsole() {

		long cityId = -1;

		System.out.print("Enter city ID : ");
		String userInput = scanner.nextLine();
		if (!userInput.isEmpty()) {
			try {
				cityId = Long.parseLong(userInput);

			} catch (NumberFormatException e) {
				System.out.println("city ID must be a long value.... update aborted");
			}
		}
		return cityId;

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

		String connectionURL = "";
		String connectionUser = "";
		String connectionPwd = "";
		try {
			String fileName = DBConnection.class.getSimpleName() + ".properties";
			System.out.println("Properties file name : " + fileName);
			Properties properties = load(fileName);

			if (properties != null) {
				connectionURL = properties.getProperty("connectionURL");
				connectionUser = properties.getProperty("connectionUser");
				connectionPwd = properties.getProperty("connectionPwd");
			}

			if (connectionURL == null || connectionURL.isEmpty() ||
					connectionPwd == null || connectionPwd.isEmpty() ||
					connectionUser == null || connectionUser.isEmpty()) {
				System.out.println("Some properties are missing... Exit program");
				return;
			}

		} catch (FileNotFoundException f) {
			System.out.println("Properties file missing.... Exit program");
			return;
		} catch (IOException io) {
			System.out.println("Properties file can't be parsed.... Exit program");
			return;
		}

		// try with resources : automatically close object initialize inside ()
		try (Connection conn = DriverManager.getConnection(connectionURL,
				connectionUser,
				connectionPwd)) {

			System.out.println("Connection established !");

			int userChoice = -1;

			while (userChoice != 0) {
				System.out.println();
				System.out.println("*************************");
				System.out.println("*  Main data base menu  *");
				System.out.println("*************************");
				System.out.println("1 -> Execute select query");
				System.out.println("2 -> Create new city");
				System.out.println("3 -> Update existing city");
				System.out.println("4 -> Delete existing city");
				System.out.println();
				System.out.println("0 -> Exit program");

				userChoice = getUserChoice(0, 4, false);

				switch (userChoice) {
				case 1:
					String selectQuery = "";
					while (selectQuery.equals("")) {
						System.out.println("");
						System.out.println("Please input your select query to execute :");
						selectQuery = scanner.nextLine();
						if (selectQuery.isEmpty()) {
							selectQuery = SELECT_QUERY_ALL_CITIES;
						}
					}

					displaySelectQueryResult(conn, selectQuery);

					break;

				case 2:
					System.out.println();
					System.out.println("Create new city");
					CityDBO newCity = getCityData(null);

					saveCity(conn, newCity);
					break;

				case 3:
					System.out.println();
					System.out.println("Update City");
					long cityId = getCityIdFromConsole();
					if (cityId != -1) {

						CityDBO city = getCityFromDataBase(conn, cityId);

						if (city != null) {
							city = getCityData(city);
							updateCity(conn, city);

						} else {
							System.out.println("No city with this ID in database... update aborted");
						}

					}

					break;
				case 4:
					System.out.println();
					System.out.println("Delete City");
					cityId = getCityIdFromConsole();
					if (cityId != -1) {
						deleteCity(conn, cityId);
					}

					break;

				}
			}

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		scanner.close();

	}

}

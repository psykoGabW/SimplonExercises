package co.simplon.java.workshop.soap.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseConnection {

  private static ResourceBundle rb = ResourceBundle.getBundle("config");
  
  /**
   * Instantiates a database connection
   * @return a {@code Connection} object if connection is successful, {@code null} otherwise
   */
  public static Connection getInstance() {
    Connection connect = null;

    try {
      Class.forName("org.postgresql.Driver");
      connect = DriverManager.getConnection(rb.getString("db-url"), rb.getString("db-user"), rb.getString("db-password"));
    } catch (SQLException e) {
      System.err.println("Connection to DB is not possible!");
    } catch (ClassNotFoundException e) {
      System.err.println("Connection to DB is not possible!");
    }

    return connect;
  }
}

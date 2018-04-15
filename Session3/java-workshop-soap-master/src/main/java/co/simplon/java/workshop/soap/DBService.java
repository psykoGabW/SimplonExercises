package co.simplon.java.workshop.soap;

import java.sql.Connection;

import co.simplon.java.workshop.soap.database.DatabaseConnection;

/**
 * Abstract service class that is meant to make calls to database.
 * 
 * @author Jules Grand
 */
public abstract class DBService {

  /**
   * {@code Connection} object that must not be modified by child services.
   */
  private Connection dbConnection = null;

  /**
   * Returns a {@code Connection} object linked to the Database.
   * 
   * @return brand new {@code Connection} object if {@code dbConnection} was null, existing one otherwise.
   */
  protected Connection getDBConnection() {
    if (dbConnection == null) {
      dbConnection = DatabaseConnection.getInstance();
    }
    return dbConnection;
  }
}

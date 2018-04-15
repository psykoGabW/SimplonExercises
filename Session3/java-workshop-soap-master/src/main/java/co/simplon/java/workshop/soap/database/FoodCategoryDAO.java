package co.simplon.java.workshop.soap.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import co.simplon.java.workshop.soap.model.FoodCategory;

public class FoodCategoryDAO extends DAO<FoodCategory> {


  /**
   * FoodCategory DAO object constructor.
   * 
   * @param conn DB Connection parameter.
   */
  public FoodCategoryDAO(Connection conn) {
    super(conn, "food_category");
  }

  @Override
  public FoodCategory create(FoodCategory obj) {
    FoodCategory createdObject = null;
    try {

      PreparedStatement createStatement = connect
          .prepareStatement("INSERT into " + tableName + " (\"name\")" + " values (?)", Statement.RETURN_GENERATED_KEYS);
      createStatement.setString(1, obj.getName());

      createStatement.executeUpdate();
      ResultSet rs = createStatement.getGeneratedKeys();

      if (rs.next()) {
        createdObject = findById(rs.getInt(pkName));
      }

    } catch (SQLException e) {
      System.err.println("Une erreur est survenue lors de la sauvegarde d'une catégorie d'aliment !");
      createdObject = null;
    }
    return createdObject;
  }

  @Override
  public boolean delete(FoodCategory obj) {
    boolean deletionSuccess = false;
    try {
      PreparedStatement deleteStatement = connect.prepareStatement("DELETE from " + tableName + " WHERE " + pkName + " = ?");
      deleteStatement.setInt(1, obj.getId());

      deleteStatement.execute();
      deletionSuccess = true;
    } catch (SQLException e) {
      System.err.println("Une erreur est survenue lors de la suppression de la catégorie : " + obj.getName());
      deletionSuccess = false;
    }
    return deletionSuccess;
  }

  @Override
  public FoodCategory findById(int id) {
    FoodCategory foodCategory = null;
    try {
      PreparedStatement createStatement = connect.prepareStatement("SELECT * from " + tableName + " where " + pkName + " = ?");
      createStatement.setInt(1, id);

      ResultSet result = createStatement.executeQuery();

      if (result.next()) {

        foodCategory = new FoodCategory(result.getInt(pkName), result.getString("name"));
      }
    } catch (SQLException e) {
      System.err.println("Une erreur est survenue lors de la lecture d'une catégorie d'aliment !");
    }
    return foodCategory;
  }
 
  /**
   * Finds a FoodCategory by its name.
   * @param name the category name to look for in DB.
   * @return First food category object matching name, {@code null} otherwise. 
   */
  public FoodCategory findByName(String name) {
    FoodCategory foodCategory = null;
    try {
      PreparedStatement createStatement = connect.prepareStatement("SELECT * from " + tableName + " where name = ?");
      createStatement.setString(1, name);

      ResultSet result = createStatement.executeQuery();

      if (result.next()) {

        foodCategory = new FoodCategory(result.getInt(pkName), result.getString("name"));
      }
    } catch (SQLException e) {
      System.err.println("Une erreur est survenue lors de la lecture d'une catégorie d'aliment !");
    }
    return foodCategory;
  }
}

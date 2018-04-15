package co.simplon.java.workshop.soap.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.simplon.java.workshop.soap.model.FoodAttribute;

public class FoodAttributeDAO extends DAO<FoodAttribute> {

  /**
   * FoodAttribute DAO object constructor.
   * 
   * @param conn DB Connection parameter.
   */
  public FoodAttributeDAO(Connection conn) {
    super(conn, "food_attribute");
  }

  @Override
  public FoodAttribute create(FoodAttribute obj) {
    FoodAttribute createdObject = null;
    try {

      PreparedStatement createStatement = connect.prepareStatement("INSERT into " + tableName
          + " (category_id, \"name\", energy, protein, carb, fat)" + " values (?, ?, ?, ?, ?, ?)",
          Statement.RETURN_GENERATED_KEYS);
      createStatement.setInt(1, obj.getCategory().getId());
      createStatement.setString(2, obj.getName());
      createStatement.setInt(3, obj.getEnergy());
      createStatement.setInt(4, obj.getProt());
      createStatement.setInt(5, obj.getCarb());
      createStatement.setInt(6, obj.getFat());
      
      createStatement.executeUpdate();
      ResultSet rs = createStatement.getGeneratedKeys();

      if (rs.next()) {
        createdObject = findById(rs.getInt(pkName));
      }

    } catch (SQLException e) {
      System.err.println("An error happened while trying to insert " + obj.getName() + " in FoodAttribute table!");
      createdObject = null;
    }
    return createdObject;
  }

  @Override
  public boolean delete(FoodAttribute obj) {
    boolean deletionSuccess = false;
    try {
      PreparedStatement deleteStatement = connect
          .prepareStatement("DELETE from " + tableName + " WHERE " + pkName + " = ?");
      deleteStatement.setInt(1, obj.getId());

      deleteStatement.execute();
      deletionSuccess = true;
    } catch (SQLException e) {
      System.err.println("An error happened while trying to remove " + obj.getName() + " from FoodAttribute table!");
      deletionSuccess = false;
    }
    return deletionSuccess;

  }

  @Override
  public FoodAttribute findById(int id) {
    FoodAttribute foodAttribute = null;
    try {
      PreparedStatement createStatement = connect
          .prepareStatement("SELECT * from " + tableName + " WHERE " + pkName + " = ?");
      createStatement.setInt(1, id);

      ResultSet result = createStatement.executeQuery();

      if (result.next()) {

        foodAttribute = new FoodAttribute(result.getInt(pkName),
            (new FoodCategoryDAO(connect)).findById(result.getInt("category_id")), result.getString("name"),
            result.getInt("energy"), result.getInt("protein"), result.getInt("carb"), result.getInt("fat"));
      }
    } catch (SQLException e) {
      System.err.println("An error happened while reading FoodAttribute table!");
    }
    return foodAttribute;
  }

  /**
   * Finds a FoodAttribute by its name.
   * 
   * @param name the name to look for in DB.
   * @return First food attribute object matching name, {@code null} otherwise.
   */
  public FoodAttribute findByName(String name) {
    FoodAttribute foodAttribute = null;
    try {
      PreparedStatement createStatement = connect.prepareStatement("SELECT * from " + tableName + " WHERE name = ?");
      createStatement.setString(1, name);

      ResultSet result = createStatement.executeQuery();

      if (result.next()) {

        foodAttribute = new FoodAttribute(result.getInt(pkName),
            (new FoodCategoryDAO(connect)).findById(result.getInt("category_id")), result.getString("name"),
            result.getInt("energy"), result.getInt("protein"), result.getInt("carb"), result.getInt("fat"));
      }
    } catch (SQLException e) {
      System.err.println("An error happened while reading FoodAttribute table!");
    }
    return foodAttribute;
  }

  /**
   * Lists FoodAttribute persisted in DB matching name.
   * 
   * @return a {@code List} of {@code T} objects.
   */
  public List<FoodAttribute> listByName(String name) {
    ArrayList<FoodAttribute> resultList = new ArrayList<>();
    try {
      PreparedStatement createStatement = connect
          .prepareStatement("SELECT * from " + tableName + " WHERE name like ?");
      createStatement.setString(1, name + "%");
      ResultSet results = createStatement.executeQuery();

      while (results.next()) {
        resultList.add(this.findById(results.getInt(pkName)));
      }
    } catch (SQLException e) {
      System.err.println("An error happened while reading from " + tableName + " DB table.");
    }
    return resultList;
  }
}
package co.simplon.java.workshop.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import co.simplon.java.workshop.soap.database.FoodAttributeDAO;
import co.simplon.java.workshop.soap.database.FoodCategoryDAO;
import co.simplon.java.workshop.soap.model.FoodAttribute;
import co.simplon.java.workshop.soap.model.FoodCategory;

@WebService(serviceName = "FoodDBService")
public class FoodDBService extends DBService {

  /**
   * Gets a food attribute based on its ID.
   * 
   * @param id the food attribute ID to look for.
   * @return the corresponding food attribute object if found, {@code false} otherwise.
   */
  @WebMethod
  @WebResult(name = "FoodAttribute")
  public FoodAttribute getFoodAttributeById(@WebParam(name = "foodAttributeId") int id) {
    FoodAttributeDAO foodDao = new FoodAttributeDAO(getDBConnection());
    return foodDao.findById(id);
  }

  /**
   * Adds a food attribute in database based on arguments properties. The category name given as argument must link to an existing category
   * in DB. If category does not exist then food attribute will not be created.
   * 
   * @param categoryName the category name of the food attribute to create.
   * @param name the food attribute name.
   * @param energy the food attribute energy (per 100g)
   * @param prot the food attribute protein weight (per 100g)
   * @param fat the food attribute fat weight (per 100g)
   * @param carb the food attribute carb weight (per 100g)
   * @return the ID of the created food attribute if creation went well, {@code null} otherwise.
   */
  @WebMethod
  public int createFoodAttribute(@WebParam(name = "categoryName") String categoryName,
      @WebParam(name = "name") String name, @WebParam(name = "energy") int energy, @WebParam(name = "prot") int prot,
      @WebParam(name = "fat") int fat, @WebParam(name = "carb") int carb) {

    FoodCategoryDAO foodCatDao = new FoodCategoryDAO(getDBConnection());
    FoodAttributeDAO foodDao = new FoodAttributeDAO(getDBConnection());

    // Look for food category in DB
    FoodCategory foodCat = foodCatDao.findByName(categoryName);
    FoodAttribute food = null;

    // If food category is not found, then food attribute cannot be created
    if (foodCat != null) {
      food = foodDao.create(new FoodAttribute(0, foodCat, name, energy, prot, fat, carb));
    }

    return (food != null) ? food.getId() : -1;
  }

  /**
   * Returns a list of food attributes based on a name filter. Filtering rule is following: method look for all food attributes where starts
   * with nameFilter.
   * 
   * @param nameFilter the filtering criteria.
   * @return a list of food attributes maching the nameFilter criteria.
   */
  @WebMethod
  public List<FoodAttribute> getFoodAttributeListByName(@WebParam(name = "nameFilter") String nameFilter) {
    FoodAttributeDAO foodDao = new FoodAttributeDAO(getDBConnection());
    return foodDao.listByName(nameFilter);
  }

}
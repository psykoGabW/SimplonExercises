package co.simplon.java.gwi.ws_caller;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import co.simplon.java.workshop.soap.FoodAttribute;
import co.simplon.java.workshop.soap.FoodDBService;
import co.simplon.java.workshop.soap.FoodDBService_Service;

public class WSCaller {

	private static Scanner scanner = new Scanner(System.in);

	private static URL url;

	private enum ElementType {

		Food("Food", "FoodAttribute"), Category("Food category", "FoodCategory");

		private String label;
		private String className;

		ElementType(String eltTypeLbl, String className) {
			this.label = eltTypeLbl;
			this.className = className;
		}

		public String getLabel() {
			return this.label;
		}

	}

	private static void displayObject(Object o) {
		Class<? extends Object> oClass = o.getClass();

		String objectDisplay = "";
		
		for (Method m : oClass.getDeclaredMethods()) {
			
			if (m.getName().contains("get")) {
				String attributeName = m.getName().replaceAll("get", "");
				String attributeValue = "";
				try {
					attributeValue = (String) oClass.getMethod(m.getName()).invoke(o).toString();
				} catch (NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					attributeValue = "Getting Value failed!";
				} catch (IllegalAccessException e) {
					attributeValue = "Getting Value failed!";
				} catch (IllegalArgumentException e) {
					attributeValue = "Getting Value failed!";
				} catch (InvocationTargetException e) {
				}
				
				objectDisplay += (objectDisplay.equals("")?"":" - ") + attributeName + " : " + attributeValue;
			}
		}
		
		System.out.println(objectDisplay);
	}

	private static void getFoodAttributeByName(String name) {
		FoodDBService_Service fDB_Service = new FoodDBService_Service(url);
		FoodDBService foodService = fDB_Service.getFoodDBServicePort();

		List<FoodAttribute> foodList = foodService.getFoodAttributeListByName(name);

		if (foodList != null) {
			for (FoodAttribute f : foodList) {
				displayObject(f);
			}

		} else {
			System.out.println("No food retrieved !");
		}
	}

	private static void displayReadMenu(ElementType element, Boolean exitProgram) {
		boolean exitMenu = false;
		exitProgram = false;

		do {
			System.out.println("******************************");
			System.out.println("          READ " + element.getLabel());
			System.out.println("******************************");
			System.out.print("Enter Food name : ");
			String foodName = scanner.nextLine();

			getFoodAttributeByName(foodName);

			System.out.println();
			System.out.println("What do you want to do : ");
			System.out.println("R -> Read another food attributes");
			System.out.println("P -> Return to previous menu");
			System.out.println("X -> Exit program");

			String[] expectedValues = { "R", "P", "X" };
			String userChoice = Utils.getUserChoice(scanner, expectedValues, false);

			switch (userChoice) {
			case "R":
				break;
			case "P":
				exitMenu = true;
				break;
			case "X":
				exitProgram = true;
				break;
			}

		} while (!exitMenu && !exitMenu);
	}

	private static void displayCRUDMenu(ElementType element, Boolean exitProgram) {

		boolean exitMenu = false;
		exitProgram = false;

		do {
			System.out.println("******************************");
			System.out.println("          CRUD MENU");
			System.out.println("******************************");
			System.out.println("C -> Create " + element.getLabel());
			System.out.println("R -> Read " + element.getLabel());
			System.out.println("U -> Update " + element.getLabel());
			System.out.println("D -> Delete " + element.getLabel());
			System.out.println();
			System.out.println("M -> Return to mainMenu");
			System.out.println("X -> Exit program");
			System.out.println();
			System.out.println("What do you want to do ?");

			String[] expectedChoices = { "C", "R", "U", "D", "M", "X" };

			String userChoice = Utils.getUserChoice(scanner, expectedChoices, false);

			switch (userChoice) {
			case "C":
			case "R":
				displayReadMenu(element, exitProgram);
				break;
			case "U":
			case "D":
			case "M":
				exitMenu = true;
				break;
			case "X":
				exitProgram = true;
				break;
			}

		} while (!exitMenu && !exitProgram);
	}

	private static void displayMainMenu() {
		boolean exitProgram = false;
		do {
			System.out.println();
			System.out.println("******************************");
			System.out.println("          MAIN MENU");
			System.out.println("******************************");
			System.out.println("F -> Food management");
			System.out.println("C -> Food Category management ");
			System.out.println();
			System.out.println("X -> Exit program");
			System.out.println();
			System.out.println("What do you want to do ?");

			String[] expectedChoices = { "F", "C", "X" };

			String userChoice = Utils.getUserChoice(scanner, expectedChoices, false);

			switch (userChoice) {
			case "F":
				displayCRUDMenu(ElementType.Food, exitProgram);
				break;
			case "C":
				System.out.println("Not implemented yet...");
				break;
			case "X":
				exitProgram = true;
			}
		} while (!exitProgram);

	}

	public static void main(String args[]) throws MalformedURLException {

		url = new URL("http://192.168.1.39:8081/soap-demo/foodDBService");

		displayMainMenu();

		FoodDBService_Service fDB_Service = new FoodDBService_Service(url);
		FoodDBService foodService = fDB_Service.getFoodDBServicePort();

		/*
		 * CategoryDBService_Service cat_Service = new CategoryDBService_Service();
		 * 
		 * CategoryDBService categoryService = cat_Service.getCategoryDBServicePort();
		 */

		List<FoodAttribute> foodList = foodService.getFoodAttributeListByName("Mal");

		System.out.println("Food list begining by Mal");
		if (foodList != null) {
			for (FoodAttribute food : foodList) {
				System.out.println(food.getId() + " - " + food.getName());
			}
		}
		System.out.println();

		/*
		 * System.out.println("Category list begining with 'S'"); List<FoodCategory>
		 * categoryList = categoryService.getFoodCategoryListByName("S"); if
		 * (categoryList == null ) {
		 * System.out.println("No category begining with 'S'"); } else { for
		 * (FoodCategory c : categoryList) { System.out.println(c.getId() + " - " +
		 * c.getName()); } }
		 */

	}

}

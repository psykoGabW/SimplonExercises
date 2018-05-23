package co.simplon.gwi.hibernate.exo01;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.simplon.gwi.hibernate.exo01.dao.CityDao;
import co.simplon.gwi.hibernate.exo01.dao.IDao;
import co.simplon.gwi.hibernate.exo01.dao.JpaGenericDao;
import co.simplon.gwi.hibernate.exo01.dao.MonumentDao;
import co.simplon.gwi.hibernate.exo01.dao.UserDao;
import co.simplon.gwi.hibernate.exo01.model.City;
import co.simplon.gwi.hibernate.exo01.model.Monument;
import co.simplon.gwi.hibernate.exo01.model.User;

public class App implements AutoCloseable {
	private final EntityManagerFactory factory;
	private final Scanner scanner = new Scanner(System.in);

	public App() {
		String persistenceUnitName = "demo-jpa-1"; // defined in persistence.xml
		Map<String, String> env = System.getenv();
		Map<String, Object> configOverrides = new HashMap<String, Object>();
		for (String envName : env.keySet()) {
			if (envName.contains("DB_USER")) {
				configOverrides.put("javax.persistence.jdbc.user", env.get(envName));
			}
			if (envName.contains("DB_PASS")) {
				configOverrides.put("javax.persistence.jdbc.password", env.get(envName));
			}
			if (envName.contains("DB_URL")) {
				configOverrides.put("javax.persistence.jdbc.url", env.get(envName));
			}
		}

		factory = Persistence.createEntityManagerFactory(persistenceUnitName, configOverrides);
	}

	private enum ElementType {
		User("Visitor", "User", "co.simplon.gwi.hibernate.exo01.dao.UserDao"), Monument("Monument", "Monument",
				"co.simplon.gwi.hibernate.exo01.dao.MonumentDao"), City("City", "City",
						"co.simplon.gwi.hibernate.exo01.dao.CityDao");

		private String label;
		private String className;
		private String daoClassName;

		ElementType(String eltTypeLbl, String className, String daoClassName) {
			this.label = eltTypeLbl;
			this.className = className;
			this.daoClassName = daoClassName;
		}

		public String getLabel() {
			return this.label;
		}

		public String getClassName() {
			return this.className;
		}

		public String getDaoClassName() {
			return this.daoClassName;
		}
	}

	/**
	 * Generic Method to display all records for City, User or Monument
	 * 
	 * @param element
	 *            : ElementType representing type of Element to display
	 * @param exitProgram
	 *            : Specify if at the end of this display program should be ended.
	 */

	private void displayAll(ElementType element) {
		EntityManager em = factory.createEntityManager();

		List<? extends Object> entitiesList = null;

		// Pagination variables
		int firstElt = 0;
		final int eltNumber = 5;

		boolean stopDisplay = false;

		System.out.println();
		System.out.println("Display of all " + element.getLabel());

		do {
			// Selection of right dao regarding managed element.
			switch (element) {
			case User:
				UserDao userDao = new UserDao(em);
				entitiesList = userDao.findAll(firstElt, eltNumber);
				break;
			case City:
				CityDao cityDao = new CityDao(em);
				entitiesList = cityDao.findAll(firstElt, eltNumber);
				break;
			case Monument:
				MonumentDao monumentDao = new MonumentDao(em);
				entitiesList = monumentDao.findAll(firstElt, eltNumber);
				break;
			default:
				stopDisplay = true;
			}

			// Process of display
			if (entitiesList != null) {
				for (Object o : entitiesList) {
					System.out.println(o.toString());
				}

				if (entitiesList.size() < eltNumber) {
					System.out.println("--- End of " + element.getLabel() + " list ---");
				}

				if (firstElt == 0 && entitiesList.size() < eltNumber) {
					System.out.println();
					System.out.println("Press [Enter] to return to previous menu");
					scanner.nextLine();
					stopDisplay = true;
				} else {
					String menu = "(S)top list";
					if (firstElt >= eltNumber) {
						menu = "(P)revious " + element.getLabel() + "s " + menu;
					}
					if (entitiesList.size() == eltNumber) {
						menu = "(N)ext " + element.getLabel() + "s " + menu;
					}

					System.out.println(menu);
					String[] expectedValues = { "P", "N", "S" };
					String userChoice = ConsoleMenuUtils.getUserChoice(scanner, expectedValues, false);

					switch (userChoice) {
					case "P":
						firstElt -= eltNumber;
						break;
					case "N":
						firstElt += eltNumber;
						break;
					default:
						stopDisplay = true;
					}
				}
			}
		} while (!stopDisplay);

		// close of em.
		em.close();
	}

	private void createUserMenu() {

		User newUser = new User();
		System.out.print("Name : ");
		newUser.setName(scanner.nextLine());

		// To do : allow user to attach this visitor to a monument.

		EntityManager em = factory.createEntityManager();
		UserDao userDao = new UserDao(em);
		em.getTransaction().begin();
		try {
			User createdUser = userDao.create(newUser);
			em.getTransaction().commit();
			System.out.println("User " + createdUser.getName() + " created with Id : " + createdUser.getId());
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	private void createMonumentMenu() {
		Monument newMonument = new Monument();

		System.out.print("Name : ");
		newMonument.setName(scanner.nextLine());

		// To do : allow user to attach this monument to a city.

		EntityManager em = factory.createEntityManager();
		MonumentDao monumentDao = new MonumentDao(em);
		em.getTransaction().begin();
		try {
			Monument createdMonument = monumentDao.create(newMonument);
			em.getTransaction().commit();
			System.out
					.println("Monument " + createdMonument.getName() + " created with Id : " + createdMonument.getId());
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	/*
	 * Method to change to allow Update
	 */
	private City modifyCity(City existingCity) {
		City newCity = new City();

		boolean updateMode = (existingCity!=null);
		
		System.out.print("Name : ");
		newCity.setName(scanner.nextLine());

		System.out.print("Latitude ([-90; +90]) : ");
		double latitude = ConsoleMenuUtils.getDouble(scanner, false, -90.0, +90.0);
		newCity.setLatitude(latitude);

		System.out.print("Longitude ([-180; +180]) : ");
		double longitude = ConsoleMenuUtils.getDouble(scanner, false, -180.00, +180.00);
		newCity.setLongitude(longitude);

		return newCity;

	}

	private void createCityMenu() {
		City newCity = modifyCity(null);

		EntityManager em = factory.createEntityManager();

		CityDao cityDao = new CityDao(em);
		em.getTransaction().begin();
		try {
			City createdCity = cityDao.create(newCity);
			em.getTransaction().commit();
			System.out.println("City " + createdCity.getName() + " created with Id : " + createdCity.getId());
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		em.close();
	}

	/**
	 * Allow to create a record on database of a unique element.
	 * 
	 * @param element
	 *            : ElementType indicating which type of element will be recorded.
	 */
	private void createRecordMenu(ElementType element) {

		boolean createNewOne = true;

		while (createNewOne) {
			System.out.println();
			System.out.println("Creation of a new " + ElementType.User.getLabel() + " ********");

			switch (element) {
			case City:
				createCityMenu();
				break;
			case Monument:
				createMonumentMenu();
				break;
			case User:
				createUserMenu();
				break;
			}

			System.out.println("Do you want to create a new " + element.getLabel() + " (Y/N)?");
			String[] expectedValues = { "Y", "N" };
			String userChoice = ConsoleMenuUtils.getUserChoice(scanner, expectedValues, false);
			switch (userChoice) {
			case "Y":
				createNewOne = true;
				break;
			case "N":
				createNewOne = false;
				break;
			default:
				createNewOne = false;
			}
		}
	}

	private void displayDeleteMenu(ElementType element) {
		System.out.println();
		System.out.println("Delete menu for : " + element.getLabel());
		System.out.print("Enter Id of " + element.getLabel() + " to delete : ");

		Long id = ConsoleMenuUtils.getLong(scanner, true);

		if (id == null) {
			System.out.println("Delete aborted...");
		} else {
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();

			try {

				// Use of genericity to practice...
				Class<?> classDaoAInstancier = Class.forName(element.getDaoClassName());
				Constructor<?> constructorDao = classDaoAInstancier.getConstructor(EntityManager.class);

				IDao<?> instanceDao = (IDao<?>) constructorDao.newInstance(em);

				instanceDao.deleteById(id);
				em.getTransaction().commit();

			} catch (Exception e) {
				em.getTransaction().rollback();
				System.out.println("An error occured during delete... Rollback has been performed ");
				e.printStackTrace();
			}
			em.close();
		}
	}

	private void readRecordMenu(ElementType element) {
		System.out.println();
		System.out.println("Read of " + element.getLabel());
		System.out.print("Please give id of " + element.getLabel() + " to read (Enter to abort) : ");

		Long id = ConsoleMenuUtils.getLong(scanner, true);

		if (id == null) {
			System.out.println("Reading aborted.");
			return;
		}

		try {
			Class<?> classDao = Class.forName(element.getDaoClassName());
			Constructor<?> daoConstructor = classDao.getConstructor(EntityManager.class);

			EntityManager em = factory.createEntityManager();
			IDao<?> dao = (IDao<?>) daoConstructor.newInstance(em);

			Object o = dao.getById(id);
			if (o == null) {
				System.out.println("No " + element.getLabel() + " found for id " + id);
			} else {
				System.out.println(o.toString());
			}

			em.close();

		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("readRecordMenu : An error occured during genericity process.");
			e.printStackTrace();
		}
	}

	private void updateRecordMenu(ElementType element) {
		System.out.println();
		System.out.println("Update of " + element.getLabel());

		System.out.print("Please enter id of " + element.getLabel() + " to update (Enter to abort): ");
		Long id = ConsoleMenuUtils.getLong(scanner, true);

		if (id == null) {
			System.out.println("Update aborted.");
			return;
		}

		EntityManager em = factory.createEntityManager();

		try {

			Class<?> classDao = Class.forName(element.daoClassName);
			Constructor<?> daoConstructor = classDao.getConstructor(ElementType.class);

			IDao<?> dao = (IDao<?>) daoConstructor.newInstance(em);

			Object o = dao.getById(id);

			if (o == null) {
				System.out.println("No " + element.getLabel() + " found for id " + id);
			} else {
				switch (element) {
				case City:
					// Todo : Méthode de modification d'une ville avec City city en parametre
					break;
				case Monument:
					// Todo : Méthode de modification d'un monument avec Monument m en param
					break;
				case User:
					// Todo : Méthode de modification d'un user avec User en param.
					break;
				}
			}
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("updateRecordMenu : An error occured during genericity process.");

			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	private void displayCRUDMenu(ElementType element, Boolean exitProgram) {

		boolean exitMenu = false;
		exitProgram = false;

		do {
			System.out.println();
			System.out.println("******************************");
			System.out.println("          CRUD MENU " + element.getLabel());
			System.out.println("******************************");
			System.out.println("C -> Create " + element.getLabel());
			System.out.println("R -> Read one" + element.getLabel());
			System.out.println("U -> Update " + element.getLabel());
			System.out.println("D -> Delete " + element.getLabel());
			System.out.println();
			System.out.println("L -> List All " + element.getLabel());
			System.out.println();
			System.out.println("M -> Return to mainMenu");
			System.out.println();
			System.out.println("What do you want to do ?");

			String[] expectedChoices = { "C", "R", "U", "D", "L", "M" };

			String userChoice = ConsoleMenuUtils.getUserChoice(scanner, expectedChoices, false);

			switch (userChoice) {
			case "C":
				createRecordMenu(element);
				break;
			case "R":
				readRecordMenu(element);
				break;
			case "U":
				updateRecordMenu(element);
				break;
			case "D":
				displayDeleteMenu(element);
				break;
			case "L":
				displayAll(element);
				break;
			case "M":
				exitMenu = true;
				break;
			}

		} while (!exitMenu);
	}

	private void displayMainMenu() {
		Boolean exitProgram = false;
		do {
			System.out.println();
			System.out.println("******************************");
			System.out.println("          MAIN MENU");
			System.out.println("******************************");
			System.out.println("C -> City management");
			System.out.println("M -> Monument management ");
			System.out.println("V -> Visitor management ");
			System.out.println();
			System.out.println("X -> Exit program");
			System.out.println();
			System.out.println("What do you want to do ?");

			String[] expectedChoices = { "C", "M", "V", "X" };

			String userChoice = ConsoleMenuUtils.getUserChoice(scanner, expectedChoices, false);

			switch (userChoice) {
			case "C":
				displayCRUDMenu(ElementType.City, exitProgram);
				break;
			case "M":
				displayCRUDMenu(ElementType.Monument, exitProgram);
				break;
			case "V":
				displayCRUDMenu(ElementType.User, exitProgram);
				break;
			case "X":
				exitProgram = true;
			}
		} while (!exitProgram);

	}

	public static void testDaoCreateCity() {
		try (App myApp = new App()) {
			EntityManager em = myApp.factory.createEntityManager();

			City city = new City("Londres", 51.5112, -0.165730);
			Monument monument = new Monument("Big ben");
			User visitor = new User("Murray Head");

			city.addMonument(monument);
			visitor.addMonument(monument);

			em.getTransaction().begin();
			CityDao cityDAO = new CityDao(em);
			cityDAO.create(city);
			em.getTransaction().commit();

			System.out.println(city);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testDaoAddAndRemoveUser() {
		try (App myApp = new App()) {
			EntityManager em = myApp.factory.createEntityManager();

			CityDao cityDao = new CityDao(em);

			City city = cityDao.getById(6L);

			Set<Monument> monuments = city.getMonuments();
			if (monuments != null && !monuments.isEmpty()) {

				Monument monument = city.getMonuments().iterator().next();
				Set<User> visitors = monument.getUsers();

				if (visitors != null && !visitors.isEmpty()) {
					User userToRemove = visitors.iterator().next();
					visitors.remove(userToRemove);
					userToRemove.getMonuments().remove(monument);
				}

				monument.addUser(new User("Valérian"));

				em.getTransaction().begin();
				cityDao.update(city);
				em.getTransaction().commit();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testJPQL() {
		try (App myApp = new App()) {
			EntityManager em = myApp.factory.createEntityManager();

			// Test of generic getByName method
			UserDao userDao = new UserDao(em);
			List<User> users = userDao.getByName("Hea");

			for (User u : users) {
				System.out.println("[User - Find Val ][" + u.getId() + " - " + u.getName() + "]");
			}

			users = userDao.findAll(0, 5);
			if (users.size() == 0) {
				System.out.println("[User - Find All ][Empty]");
			} else {
				for (User u : users) {
					System.out.println("[User - Find All ][" + u.getId() + " - " + u.getName() + "]");
				}
			}

			/*
			 * users = em.createNamedQuery("User.findAll", User.class).getResultList(); for
			 * (User u : users) { System.out.println("[User - Find All][" + u.getId() +
			 * " - " + u.getName() + "]"); } User userToDelete = users.get(users.size()-1);
			 * System.out.println("Id to delete : " + userToDelete.getId());
			 * 
			 * em.getTransaction().begin();
			 * em.createNamedQuery("User.deleteById").setParameter("id",
			 * userToDelete.getId()).executeUpdate();
			 * 
			 * 
			 * users = em.createNamedQuery("User.findAll", User.class).getResultList(); for
			 * (User u : users) { System.out.println("[User - After Delete][" + u.getId() +
			 * " - " + u.getName() + "]"); } em.getTransaction().rollback();
			 */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testCrud() {
		try (App myApp = new App()) {
			myApp.displayMainMenu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// testExo01();
		// testDaoCreateCity();
		// testDaoAddAndRemoveUser();
		// testJPQL();
		testCrud();
	}

	public void close() throws Exception {
		// TODO Auto-generated method stub
		factory.close();

	}
}

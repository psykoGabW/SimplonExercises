package co.simplon.gwi.hibernate.exo01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.simplon.gwi.hibernate.exo01.dao.CityDao;
import co.simplon.gwi.hibernate.exo01.dao.UserDao;
import co.simplon.gwi.hibernate.exo01.model.City;
import co.simplon.gwi.hibernate.exo01.model.Monument;
import co.simplon.gwi.hibernate.exo01.model.User;

public class App implements AutoCloseable {
	private EntityManagerFactory factory;

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

	public static void main(String[] args) {
		// testExo01();
		// testDaoCreateCity();
		// testDaoAddAndRemoveUser();
		testJPQL();
	}

	public void close() throws Exception {
		// TODO Auto-generated method stub
		factory.close();

	}
}

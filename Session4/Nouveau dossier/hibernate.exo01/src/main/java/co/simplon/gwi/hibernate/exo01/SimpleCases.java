package co.simplon.gwi.hibernate.exo01;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.simplon.gwi.hibernate.exo01.model.City;
import co.simplon.gwi.hibernate.exo01.model.Monument;
import co.simplon.gwi.hibernate.exo01.model.User;

public class SimpleCases implements AutoCloseable {

	private EntityManagerFactory factory;

	public City createCity() {
		EntityManager em = factory.createEntityManager();
		City city = new City("Atlantis", 0, 0.5);
		city = create(em, city);
		em.close();
		return city;
	}

	private City createAndUpdateCity() {
		EntityManager em = factory.createEntityManager();
		City city = new City("Paris", 0, 0.5);
		em.getTransaction().begin();
		em.persist(city);
		city.setLongitude(100.00);
		em.getTransaction().commit();
		em.close();
		return city;
	}

	private City create(EntityManager em, City city) {
		em.getTransaction().begin();
		em.persist(city);
		em.getTransaction().commit();
		return city;
	}

	private City readCity(EntityManager em, long id) {
		return em.find(City.class, id);
	}

	public City readCity(long id) {
		EntityManager em = factory.createEntityManager();
		City city = readCity(em, id);
		em.close();
		return city;
	}

	public City updateCity() {
		return update(new City(3L, "PaRiS", -1., -2.));
	}

	public City update(City city) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		city = em.merge(city); // If there's not city with corresponding id, a new record is created in
								// database with automatic generated Id (not the one contained in the object)
		em.getTransaction().commit();
		return city;
	}

	public void removeById(long id) {
		EntityManager em = factory.createEntityManager();
		City city = em.find(City.class, id);
		remove(em, city);
		em.close();
	}

	public void remove(EntityManager em, City city) {
		if (city == null) {
			return;
		}
		em.getTransaction().begin();
		em.remove(city);
		em.getTransaction().commit();
	}

	public Monument createMonument(long cityId, Monument monument) {

		EntityManager em = this.factory.createEntityManager();
		em.getTransaction().begin();
		City dboCity = em.find(City.class, cityId);
		dboCity.addMonument(monument);

		em.getTransaction().commit();
		em.close();
		return monument;
	}

	public User createUser() {

		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Monument existingMonument = em.find(Monument.class, 1L);

		User visitor;
		if (existingMonument != null) {
			visitor = new User("Johnny English");

			System.out.println("Monument trouvé : " + existingMonument.toString());
			visitor.addMonument(existingMonument); // existingMonument is managed by em. This user should be stored in
													// database.

		} else {
			visitor = new User("Johnny Begood");

			Monument monument = new Monument("Louxor pyramid");

			System.out.println("Creation de Louxor et de la pyramide");
			City city = new City("Louxor", 25.687243, 32.639637);

			city.addMonument(monument);
			visitor.addMonument(monument);

			em.persist(city); // This step create city & monument
		}
		// em.persist(visitor); // This step create user
		em.getTransaction().commit();
		em.close();

		return visitor;
	}

	public SimpleCases() {
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

	private static void testExo01() {
		try (SimpleCases myApp = new SimpleCases()) {
			/*
			 * User v = myApp.createUser(); System.out.println(v);
			 */
			myApp.removeById(5l);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		this.factory.close();
		
	}

}

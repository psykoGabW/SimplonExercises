package co.simplon.gwi.hibernate.exo01.dao;

import javax.persistence.EntityManager;

import co.simplon.gwi.hibernate.exo01.model.City;

public class CityDao extends JpaGenericDao<City> {

	public CityDao(EntityManager em) {
		super(City.class, em);
		// TODO Auto-generated constructor stub
	}
		
}

package co.simplon.gwi.hibernate.exo01.dao;

import javax.persistence.EntityManager;

import co.simplon.gwi.hibernate.exo01.model.Monument;

public class MonumentDao extends JpaGenericDao<Monument> {
	public MonumentDao(EntityManager em) {
		super(Monument.class, em);
	}
}

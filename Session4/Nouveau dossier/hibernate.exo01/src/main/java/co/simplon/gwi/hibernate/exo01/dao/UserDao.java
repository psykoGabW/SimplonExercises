package co.simplon.gwi.hibernate.exo01.dao;

import javax.persistence.EntityManager;

import co.simplon.gwi.hibernate.exo01.model.User;

public class UserDao extends JpaGenericDao<User> {

	public UserDao(EntityManager em) {
		super(User.class, em);
		// TODO Auto-generated constructor stub
	}
	
}

package co.simplon.gwi.hibernate.exo01.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import co.simplon.gwi.hibernate.exo01.model.User;

public abstract class JpaGenericDao<T> implements IDao<T> {

	private final Class<T> classType;
	protected final EntityManager entityManager;

	/**
	 * Constructor
	 * @param classType : Class of managed object
	 * @param em : Entity Manager to use in this DAO
	 */
	public JpaGenericDao(Class<T> classType, EntityManager em) {
		// TODO Auto-generated constructor stub
		this.classType = classType;
		this.entityManager = em;
	}

	@Override
	public T create(T object) {
		// TODO Auto-generated method stub

		this.entityManager.persist(object);

		return object;
	}

	@Override
	public T getById(Long id) {
		// TODO Auto-generated method stub

		T object = this.entityManager.find(this.classType, id);

		return object;
	}

	@Override
	public T update(T object) {
		// TODO Auto-generated method stub

		T updatedObject = this.entityManager.merge(object);

		return updatedObject;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

		T object = this.entityManager.find(this.classType, id);
		if (object != null) {
			this.entityManager.remove(object);
		}
	}

	/**
	 * This method return a list of instances with a name matching with the one passed. 
	 * @param name : name (full or partial) to search
	 * @return : List of instances
	 */
	public List<T> getByName(String name) {
		String searchParam = "%" + name + "%";
		String litteralQuery = "Select o from " + this.classType.getSimpleName()
				+ " o where o.name like ?1 order by o.name ASC";
		TypedQuery<T> query = this.entityManager.createQuery(litteralQuery, this.classType);
		query.setParameter(1, searchParam);
		return query.getResultList();
	}
	
	public List<T> findAll(int first, int size) {
		return entityManager.createNamedQuery(this.classType.getSimpleName()+".findAll", this.classType).setFirstResult(first).setMaxResults(size).getResultList();
	}
	
}

package co.simplon.gwi.hibernate.exo01.dao;

public interface IDao<T> {
	public T create(T object);
	public T getById(Long id);
	public T update(T object);
	void deleteById(Long id);
}

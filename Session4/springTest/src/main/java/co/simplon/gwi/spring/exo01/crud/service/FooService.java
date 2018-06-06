package co.simplon.gwi.spring.exo01.crud.service;
import java.util.List;
import java.util.Optional;

import javax.inject.Named;

import co.simplon.gwi.spring.exo01.crud.model.Foo;

@Named
public interface FooService {
	public Foo saveFoo(Foo foo);
	public List<Foo> getAll();
	public Optional<Foo> findbyId(Long id);
	public void deleteById(Long id);
	public List<Foo> findByName(String name);
// TODO ajouter les autres méthodes
}

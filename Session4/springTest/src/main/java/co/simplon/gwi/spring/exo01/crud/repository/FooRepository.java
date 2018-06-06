package co.simplon.gwi.spring.exo01.crud.repository;

import java.util.List;
import java.util.Optional;

import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import co.simplon.gwi.spring.exo01.crud.model.Foo;

@Named
public interface FooRepository extends JpaRepository<Foo, Long> {
	List<Foo> findByName(String name);
	Optional<Foo> findById(Long Id);
	public List<Foo> findByNameLike(@Param("name") String name);
	public void deleteById(Long arg0);
}

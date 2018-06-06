package co.simplon.gwi.spring.exo01.crud.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.inject.Named;

import co.simplon.gwi.spring.exo01.crud.model.Foo;
import co.simplon.gwi.spring.exo01.crud.repository.FooRepository;
import co.simplon.gwi.spring.exo01.crud.service.FooService;

@Named
public class FooServiceImpl implements FooService {

	@Inject
	FooRepository fooRepository;
	
	@Override
	public Foo saveFoo(Foo foo) {
		// TODO Auto-generated method stub
		
		return fooRepository.save(foo);
	}

	@Override
	public List<Foo> getAll() {
		// TODO Auto-generated method stub
		return fooRepository.findAll();
	}

	@Override
	public Optional<Foo> findbyId(Long id) {
		// TODO Auto-generated method stub
		return fooRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		fooRepository.deleteById(id);
	}

	@Override
	public List<Foo> findByName(String name){
		return fooRepository.findByName(name);
	}
	
}

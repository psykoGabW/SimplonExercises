package co.simplon.gwi.spring.exo01.crud.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

import co.simplon.gwi.spring.exo01.crud.model.Foo;

@Controller
@RequestMapping("/foos")
public class FooController {

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Foo> findAll() {
		List<Foo> tmpLst = new ArrayList<Foo>();
		tmpLst.add(new Foo(1, "JCVD"));
		tmpLst.add(new Foo(2, "Deadpool"));
		tmpLst.add(new Foo(3, "Chuck"));
		
		return tmpLst;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Foo read(@PathVariable("id") Long id) {
	  return new Foo(id, "fake read");
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Long create(@RequestBody Foo resource) {
		System.out.println("Resource received : " + resource.toString());
	    return 1L;
	}
	
	
}

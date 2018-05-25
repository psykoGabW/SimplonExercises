package co.simplon.gwi.spring.exo01.crud.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.simplon.gwi.spring.exo01.crud.model.Foo;
import co.simplon.gwi.spring.exo01.crud.service.FooService;

@Controller
@RequestMapping("/foos")
public class FooController {

	private final static String COOKIE_ID = "c00k13spr1ngt35t";

	@Inject
	FooService fooService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Foo> findAll() {
		return fooService.getAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Foo> getFooById(@PathVariable(value = "id") Long userId) {
		Optional<Foo> res = fooService.findbyId(userId);
		return res.isPresent() ? ResponseEntity.ok().body(res.get()) : ResponseEntity.notFound().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Foo update(@PathVariable("id") Long id, @RequestBody Foo resource) {

		System.out.println("Update of foo : " + resource.toString());

		if (resource.getId() == null || resource.getId() <= 0) {
			System.out.println("Erreur d'Id");
			return null;
		}
		return fooService.saveFoo(resource);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Long create(@RequestBody Foo resource) {
		System.out.println("coucou ");
		System.out.println("Resource received : " + resource.toString());
		
		Foo returnedFoo = fooService.saveFoo(resource);
		System.out.println("Resource returned : " + returnedFoo.toString());
		Long id = (returnedFoo != null ? returnedFoo.getId() : null);

		return id;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String delete(@PathVariable("id") Long id) {
		String deleteReturn = "Delete of foo with id : " + id;
		System.out.println(deleteReturn);
		fooService.deleteById(id);
		return deleteReturn;
	}

	@RequestMapping(value = "/cookie", method = RequestMethod.GET)
	@ResponseBody
	public Integer displayCookie(@CookieValue(name = COOKIE_ID, required = false, defaultValue = "0") String cookie,
			HttpServletResponse response) {

		Integer count = Integer.parseInt(cookie.toString());
		count++;

		cookie = Integer.toString(count);

		response.addCookie(new Cookie(COOKIE_ID, cookie));

		return count;
	}

	@RequestMapping(value = "/testParameter", method = RequestMethod.GET)
	@ResponseBody
	public String testParameter(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "nickname") Optional<String> nickname) {

		Objects.requireNonNull(name, "Name can't be null");
		String result = "Name is : " + name + "<br/>Nickname is : ";

		if (nickname.isPresent()) {
			result += nickname.get();
		} else {
			result += "Anonymous";
		}

		result += "<br/>Result of search by name :";
		List<Foo> foos = fooService.findByName(name);
		for (Foo foo : foos) {
			result += "<br/> " + foo.toString();
		}
		
		return result;
	}

	@RequestMapping(value = "/testMultiParam", method = RequestMethod.GET)
	@ResponseBody
	public String testMulti(@RequestParam Map<String, String> parameters) {
		String htmlResponse = "";

		if (parameters != null) {
			if (!parameters.isEmpty()) {
				for (String key : parameters.keySet()) {
					htmlResponse += (key + " : " + parameters.get(key) + "<br/>");
				}
			}
		}

		return htmlResponse;
	}

}

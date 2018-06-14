package com.example.demo;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	/*
	@RequestMapping(value = "/np/private", method = RequestMethod.GET)
	@ResponseBody
	public String getPrivateBySimplePath() {
	    return "Private test";
	}
	*/
	
	@Autowired
	MyService myService;	
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public Map<String,String> getUserName(Principal param) {
		HashMap<String, String> returnMap = new HashMap<String, String>();
		System.out.println(param);
		returnMap.put("name", param.getName());
		return (returnMap);
	}
	
	@RequestMapping(value="/np/private", method=RequestMethod.GET)
	@ResponseBody
	public String privateInfo() {
		return myService.privateInfo();
	}
	
	@RequestMapping("/np/admin")
	public String adminInfo() {
		return myService.adminInfo();
	}
	@RequestMapping("/np")
	public String np() {
		return myService.publicInfo();
	}
}

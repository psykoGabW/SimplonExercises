package com.example.demo;

import javax.inject.Named;

@Named
public class MyService {
	   public String publicInfo() {
			  return "for anybody";
		   }
		   public String adminInfo() {
			  return "for admin only";
		   }
		   public String privateInfo() {
			  return "for user";
		   }
}

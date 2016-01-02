package com.microservice.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@author Vijendra
 *
 */

@RestController
public class DemoController {
	static Logger log = Logger.getLogger(DemoController.class);

	public DemoController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/hello")
	public String hello() {
		log.info("invoking rest service...");
		return "Hello Surfer!!!";
	}
	
	@RequestMapping("/login")
	public String login() {
		log.info("invoking login module...");
		return "Login View Here";
	}
}



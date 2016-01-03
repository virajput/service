package com.microservice.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.entity.User;
import com.microservice.repository.UserRepository;

/**
 *@author Vijendra
 *
 */

@RestController
public class DemoController {
	static Logger log = Logger.getLogger(DemoController.class);
	
	@Autowired
    private UserRepository users;

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
	
	@RequestMapping("/validate")
	public User validateUser(
			@RequestParam(value="username", required=true) String username,
			@RequestParam(value="password", required=true) String password) {
		log.info("validate user");
		return users.getUserByNameAndPassword(username, password);
	}

}



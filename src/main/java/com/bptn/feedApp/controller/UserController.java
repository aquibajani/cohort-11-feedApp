package com.bptn.feedApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// REST -> REpresentational State Transfer
@RestController
@RequestMapping("/user")
public class UserController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/test")
	public String testController() {
		logger.debug("The testController() method was invoked!");
		return "<h1>The FeedApp application is up and running</h1>";
	}
	
	
}

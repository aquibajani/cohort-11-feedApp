package com.bptn.feedApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.bptn.feedApp.service.UserService;
import java.util.List;
import java.util.Optional;

import com.bptn.feedApp.jdbc.UserBean;
import com.bptn.feedApp.jpa.User;

import org.springframework.web.bind.annotation.PathVariable;
import java.sql.Timestamp;
import java.time.Instant;

// REST -> REpresentational State Transfer
@RestController
@RequestMapping("/user")
public class UserController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	@GetMapping("/test")
	public String testController() {
		logger.debug("The testController() method was invoked!");
		return "<h1>The FeedApp application is up and running</h1>";
	}
	
	@GetMapping("/")
	public List<User> listUsers() {
		logger.debug("The listUsers() method was invoked!");
		return this.userService.listUsers();
	}
	
	@GetMapping("/{username}")
	public Optional<User> findByUsername(@PathVariable String username) {
		logger.debug("The findByUsername() method was invoked!, username={}", username);
		return this.userService.findByUsername(username);
	}
	
	@GetMapping("/{first}/{last}/{username}/{password}/{phone}/{emailId}")
	public String createUser( @PathVariable String first, @PathVariable String last, @PathVariable String username, @PathVariable String password, @PathVariable String phone, @PathVariable String emailId) {
		User user = new User();
        
		user.setFirstName(first);
		user.setLastName(last);
		user.setUsername(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setEmailId(emailId);
		user.setEmailVerified(false);
		user.setCreatedOn(Timestamp.from(Instant.now()));
		        
		logger.debug("The createUser() method was invoked!, user={}", user.toString());
		        
		this.userService.createUser(user);
		        
		return "User Created Successfully";
}
	
}

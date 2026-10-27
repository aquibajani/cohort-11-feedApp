package com.bptn.feedApp.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.bptn.feedApp.repository.UserRepository;
import com.bptn.feedApp.jpa.User;
import java.util.Optional;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	    
	@Autowired
	EmailService emailService;
	
	public List<User> listUsers() {
		return this.userRepository.findAll();
	}
	
	public Optional<User> findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}
	
//	public void createUser(User user) {
//		this.userRepository.save(user);
//	}
	
	public User signup(User user){
		user.setUsername(user.getUsername().toLowerCase());
		user.setEmailId(user.getEmailId().toLowerCase());
		user.setEmailVerified(false);
		user.setCreatedOn(Timestamp.from(Instant.now()));
		this.userRepository.save(user);
		this.emailService.sendVerificationEmail(user);
		return user;
	}
	
	
}

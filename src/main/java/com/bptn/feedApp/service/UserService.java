package com.bptn.feedApp.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.bptn.feedApp.repository.UserRepository;
import com.bptn.feedApp.exception.domain.EmailExistException;
import com.bptn.feedApp.exception.domain.UsernameExistException;
import com.bptn.feedApp.jpa.User;
import java.util.Optional;
import java.sql.Timestamp;
import java.time.Instant;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	    
	@Autowired
	EmailService emailService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
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
		this.validateUsernameAndEmail(user.getUsername(), user.getEmailId());
		user.setEmailVerified(false);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		user.setCreatedOn(Timestamp.from(Instant.now()));
		this.userRepository.save(user);
		this.emailService.sendVerificationEmail(user);
		return user;
	}
	
	private void validateUsernameAndEmail(String username, String emailId) {

        this.userRepository.findByUsername(username).ifPresent(u -> {
            throw new UsernameExistException(String.format("Username already exists, %s", u.getUsername()));
        });

        this.userRepository.findByEmailId(emailId).ifPresent(u -> {
            throw new EmailExistException(String.format("Email already exists, %s", u.getEmailId()));
        });

}
	
	
}

package com.bptn.feedApp.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.bptn.feedApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.bptn.feedApp.jpa.User;
import java.util.Optional;

@Service("UserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	   Optional<User> opt = this.userRepository.findByUsername(username);

	   if (opt.isEmpty()) {
	      throw new UsernameNotFoundException("Username not found: " + username);
	   }

	   return new CustomUserDetails(opt.get());
	}
}
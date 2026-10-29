package com.bptn.feedApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import com.bptn.feedApp.filter.CustomAuthEntryPoint;
import com.bptn.feedApp.filter.JwtAuthorizationFilter;
import com.bptn.feedApp.provider.ResourceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	ResourceProvider provider;
	 
	@Autowired
	JwtAuthorizationFilter jwtAuthorizationFilter;

	@Autowired
	CustomAuthEntryPoint customAuthEntryPoint;
	
	@Bean
	PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean("test")
	SecurityFilterChain securityFilterChainTest(HttpSecurity http) throws Exception {
	    
	    http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests((requests) -> requests
	            // Replaced PathRequest.toH2Console() with the direct String path
	            .requestMatchers("/h2-console/**").permitAll() 
	            // Pass your String[] array directly into requestMatchers!
	            .requestMatchers(this.provider.getJwtExcludedUrls()).permitAll()
	            .anyRequest().authenticated())
	            .exceptionHandling((handler)-> handler.authenticationEntryPoint(this.customAuthEntryPoint))
	            .addFilterBefore(this.jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
	            .headers((headers) -> headers.frameOptions((frame) -> frame.sameOrigin()))
	            .cors(withDefaults())
	            .csrf((csrf) -> csrf.disable());
	    
	        return http.build();
	}
	
	@Bean
	@ConditionalOnMissingBean(SecurityFilterChain.class)
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        
	        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	          .authorizeHttpRequests((requests) -> requests
	          // Pass your String[] array directly here too
	          .requestMatchers(this.provider.getJwtExcludedUrls()).permitAll()
	          .anyRequest().authenticated())
	          .exceptionHandling((handler)-> handler.authenticationEntryPoint(this.customAuthEntryPoint))       
	          .addFilterBefore(this.jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
	          .cors(withDefaults())
	          .csrf((csrf) -> csrf.disable());
	        
	        return http.build();
	}
}
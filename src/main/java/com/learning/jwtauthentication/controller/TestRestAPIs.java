package com.learning.jwtauthentication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.jwtauthentication.model.User;
import com.learning.jwtauthentication.repository.UserRepository;
import com.learning.jwtauthentication.security.jwt.JwtAuthTokenFilter;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class TestRestAPIs {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/api/test/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		return ">>> User Contents!";
	}

	@GetMapping("/api/test/pm")
	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
	public String projectManagementAccess() {
		return ">>> Board Management Project";
	}
	
	@GetMapping("/api/test/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Contents";
	}
	
	
	@GetMapping("/api/test/users")
	public List<User> getUsers() {
		System.out.println(JwtAuthTokenFilter.getCurrentUserLogin());
		List<User>users = userRepository.findAll();
		return users;
	}
}
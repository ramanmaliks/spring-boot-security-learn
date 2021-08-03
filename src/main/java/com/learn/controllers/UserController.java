package com.learn.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.models.User;
import com.learn.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	// all users
	//@RequestMapping("/", Get)
	@GetMapping("/")
	public List<User> getAllUsers(){
		return this.userService.getAllUsers();	
	}
	
	//return single user
	// for Admin access only and declare mysecurityconfig @EnableGlobalMEthodSecurity(prePostEnabled = true)
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}
	
	//add a user
	@PostMapping("/")
	//Request Body automatically converts data as json and send it via post
	public User add(@RequestBody User user)
	{
		return this.userService.addUser(user);
	}
}

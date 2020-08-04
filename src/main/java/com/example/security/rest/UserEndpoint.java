package com.example.security.rest;

import com.example.security.controller.UserService;
import com.example.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserEndpoint {
	@Autowired
    UserService us;

	@GetMapping("/users")
	public Iterable <User> getAllUsers() {
		return us.getAllUsers();
	}

	@GetMapping("/users/{id}")
	public Object getUserById(@PathVariable(value = "id")long id) {
		return us.getUserById(id);
	}

}

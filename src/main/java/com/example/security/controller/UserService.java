package com.example.security.controller;

import com.example.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
	@Autowired
	UserRepository ur;

	public Iterable<User> getAllUsers() {
		System.out.println("You Requested All Users");
		return ur.findAll();
	}

	public Object getUserById(long id) {
		try {
			System.out.println("You Requested User with Id: "+ id);
			return ur.findById(id).get();
		}
		catch(Exception e1){
			System.out.println("You requested an Id that doesnt exist");
			return "<h1>You Requested User Id That Doesn't Exist</h1>";
		}
	}

	public User addUser(User newUser) {
		System.out.println("You Added A New User");
		return ur.save(newUser);
	}

	public String deleteUserById(long id) {
		System.out.println("You deleted userId: " + id);
		ur.deleteById(id);
		return "You Deleted User With Id: " + id;
	}

	public User updateUser(long id, User userInput) {
		System.out.println("Updating UserId: "+ id);
		User user = ur.findById(id).get();
		
		if (userInput.getName() != null && userInput.getName() !="") {
			user.setName(userInput.getName());
		}
		return ur.save(user);
	}
}

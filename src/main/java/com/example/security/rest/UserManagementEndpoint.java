package com.example.security.rest;

import com.example.security.controller.UserService;
import com.example.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("management/api")
public class UserManagementEndpoint {
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

    @PostMapping("/users")
    public User registerNewUser(@RequestBody User newUser) {
        return us.registerNewUser(newUser);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable(value= "id")long id) {
        us.deleteUserById(id);
        return "You Deleted User With Id: " + id;
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable(value="id")long id, @RequestBody User userInput) {
        return us.updateUser(id, userInput);
    }
}

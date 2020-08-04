package com.example.security.rest;

import com.example.security.controller.UserService;
import com.example.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("management/api")
public class UserManagementEndpoint {
    @Autowired
    UserService us;

    @GetMapping("/users")
    @PreAuthorize("HasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public Iterable <User> getAllUsers() {
        return us.getAllUsers();
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public Object getUserById(@PathVariable(value = "id")long id) {
        return us.getUserById(id);
    }

    @PostMapping("/users")
    @PreAuthorize("hasAuthority('student:write')")
    public User registerNewUser(@RequestBody User newUser) {
        return us.registerNewUser(newUser);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public String deleteUserById(@PathVariable(value= "id")long id) {
        us.deleteUserById(id);
        return "You Deleted User With Id: " + id;
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public User updateUser(@PathVariable(value="id")long id, @RequestBody User userInput) {
        return us.updateUser(id, userInput);
    }
}

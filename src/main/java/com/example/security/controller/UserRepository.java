package com.example.security.controller;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.security.model.User;

@Component
public interface UserRepository extends CrudRepository<User, Long>{

}

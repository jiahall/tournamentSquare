package com.jiahall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jiahall.model.User;
import com.jiahall.service.UserService;


@RestController
public class UserController {
	
	 @Autowired
	UserService userService;
	 //works
	 @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET, headers = "Accept=application/json")
	 public List getUsers() {
	  
	  List listOfUsers = userService.getAllUsers();
	  return listOfUsers;
	 }
	 
	 @RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	 public User getUserById(@PathVariable("id") String id) {
	  return userService.getUser(id);
	 }
	 
	 @RequestMapping(value = "/addUser", method = RequestMethod.POST, headers = "Accept=application/json")
	 public void addUser(@RequestBody User user) { 
	  userService.addUser(user);
	  
	 }
	 //works
	 @RequestMapping(value = "/updateUser", method = RequestMethod.PUT, headers = "Accept=application/json")
	 public void updateUser(@RequestBody User user) {
	  userService.updateUser(user);
	 }
	 //works
	 @RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	 public void deleteUser(@PathVariable("id") String id) {
	  userService.deleteUser(id);  
	 } 
	}


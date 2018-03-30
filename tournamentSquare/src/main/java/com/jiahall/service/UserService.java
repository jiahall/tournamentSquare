package com.jiahall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiahall.dao.UserDAO;
import com.jiahall.model.User;

import java.util.List;

@Service("userService")
public class UserService {

	 
	 @Autowired
	 UserDAO userDao;
	 
	 @Transactional
	 public List getAllUsers() {
	  return userDao.getAllUsers();
	 }
	 
	 @Transactional
	 public User getUser(String id) {
	  return userDao.getUser(id);
	 }
	 
	 @Transactional
	 public void addUser(User user) {
		 userDao.addUser(user);
	 }
	 
	 @Transactional
	 public void updateUser(User user) {
		 userDao.updateUser(user);
	 
	 }
	 
	 @Transactional
	 public void deleteUser(String id) {
	  userDao.deleteUser(id);
	 }
	}
	 
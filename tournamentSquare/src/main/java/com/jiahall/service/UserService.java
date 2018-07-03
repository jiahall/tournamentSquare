package com.jiahall.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiahall.dao.UserDAO;
import com.jiahall.jsonBuilder.JsonReply;
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
	 
	 @Transactional
	 public int registerCheck(String email, String userName, String password) {
		
		int result = userDao.insertUser(email, userName, password);
		return result;
	}
	 @Transactional
	public int loginCheck(String email, String password) {
		int result = userDao.checkUser(email,  password);
		return result;
	}

	 @Transactional
	public String getusername(String email) {
		String username = userDao.checkUser(email);
		return username;
	}



}
	
	 
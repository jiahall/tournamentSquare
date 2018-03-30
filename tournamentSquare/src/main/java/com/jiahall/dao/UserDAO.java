package com.jiahall.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiahall.model.User;

@Repository
public class UserDAO {

	 @Autowired
	 private SessionFactory sessionFactory;
	 
	 public void setSessionFactory(SessionFactory sf) {
	  this.sessionFactory = sf;
	 }
	 
	 public List getAllUsers() {
	  Session session = this.sessionFactory.getCurrentSession();
	  List userList = session.createQuery("from User").list();
	  return userList;
	 }
	 
	 public User getUser(String id) {
	  Session session = this.sessionFactory.getCurrentSession();
	  User user = (User) session.load(User.class, new String(id));
	  return user;
	 }
	 
	 public User addUser(User user) {
	  Session session = this.sessionFactory.getCurrentSession();
	  session.persist(user);
	  return user;
	 }
	 
	 public void updateUser(User user) {
	  Session session = this.sessionFactory.getCurrentSession();
	  session.update(user);
	 }
	 
	 public void deleteUser(String id) {
	  Session session = this.sessionFactory.getCurrentSession();
	  User p = (User) session.load(User.class, new String(id));
	  if (null != p) {
	   session.delete(p);
	  }
	 } 
	}
package com.jiahall.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
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

	public int insertUser(String email, String userName, String passWord) {
		Session session = this.sessionFactory.getCurrentSession();
		User bort = new User(userName, email, "no", passWord);
		session.persist(bort);
		return 1;
	}

	public int checkUser(String email, String passWord) {
		int response = 4;
		Session session = this.sessionFactory.getCurrentSession();
		List userlist = session
				.createQuery("from User where email = '" + email + "'").list();

		if (userlist.size() == 1) {
			if (BCrypt.checkpw(passWord, ((User) userlist.get(0)).getPassWord()))
			    System.out.println("It matches");
			else
			    System.out.println("It does not match");
			response = 1;
		} else {
			response = 2;
		}

		System.out.println(userlist.get(0).toString());
		return response;
	}

	public String checkUser(String email) {
		Session session = this.sessionFactory.getCurrentSession();
		List userlist = session
				.createQuery("from User where email = '" + email + "'").list();
		String response =((User) userlist.get(0)).getUserName();
		
		return response;
	}

}
package com.jiahall.service;


import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiahall.dao.TournamentDAO;
import com.jiahall.jsonBuilder.JsonReply;
import com.jiahall.model.Tournament;

import java.util.List;

@Service("tournamentService")
public class TournamentService {

	 
	 @Autowired
	 TournamentDAO tournamentDao;
	 
	 @Transactional
	 public List getAllTournaments() {
	  return tournamentDao.getAllTournaments();
	 }

	 @Transactional
	public List getTournaments(String criteria) {
		 return tournamentDao.getTournaments(criteria);
		
	}

	 @Transactional
	public List getHostedTournaments(String host) {
		return tournamentDao.getHostedTournaments(host);
	}

	 @Transactional
	public int addTournament(Tournament tournament) {
		 return tournamentDao.addTournament(tournament);
	}

	 @Transactional
	public void deleteTournament(String id) {
		 tournamentDao.deleteTournament(id);
		
	}

	 @Transactional
	public int updateTournament(Tournament tournament) {
		 int result =tournamentDao.updateTournament(tournament);
		return  result;
		
	}

	 @Transactional
	public int jointournament(String tournament) {
		 int result =tournamentDao.joinTournament(tournament);
		return  result;
	}

	 
/*	 @Transactional
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
*/


}
	
package com.jiahall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiahall.dao.EntrantDAO;
import com.jiahall.dao.TournamentDAO;
import com.jiahall.model.Entrant;
@Service("entrantService")
public class EntrantService {

	 @Autowired
	 EntrantDAO entrantDao;
	 
	 
	 @Transactional
	public List getEntrants(String tournament) {
		
		return entrantDao.getEntrants(tournament);
	}

	 @Transactional
	public int registerEntrant(Entrant entrant) {
		
		return entrantDao.registerEntrant(entrant);
	}

}

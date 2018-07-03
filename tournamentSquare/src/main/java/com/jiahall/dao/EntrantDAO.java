package com.jiahall.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import com.jiahall.model.Entrant;
import com.jiahall.model.Tournament;
import com.jiahall.model.User;
@Repository
public class EntrantDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public  List getEntrants(String tournament) {
			Session session = this.sessionFactory.getCurrentSession();
			List entrantList = session.createQuery("from Entrant WHERE tournament = '" + tournament + "'" ).list();
			return entrantList;
		}

		
	

	public int registerEntrant(Entrant entrant) {
		
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(entrant);
		return 1;
	}
	
	
	
}


	
//	public class TournamentDAO {
//
//		@Autowired
//		private SessionFactory sessionFactory;
//
//		public void setSessionFactory(SessionFactory sf) {
//			this.sessionFactory = sf;
//		}
//
//		public List getAllTournaments() {
//			Session session = this.sessionFactory.getCurrentSession();
//			List tournamentList = session.createQuery("from Tournament WHERE enterAble = 'y' AND currEntrants < maxEntrants").list();
//			return tournamentList;
//		}

//		public List getTournaments(String criteria) {
//			Session session = this.sessionFactory.getCurrentSession();
//			List tournamentList = session
//					.createQuery("from Tournament where ( name LIKE '%" + criteria + "%'"+
//			" OR postcode LIKE '%" + criteria + "%'"+" OR game LIKE '%" + criteria + "%') AND NOT enterAble = 'n' AND currEntrants < maxEntrants" ).list();
//			
//			
//			return tournamentList;
//		}
//
//		public List getHostedTournaments(String host) {
//			Session session = this.sessionFactory.getCurrentSession();
//			List tournamentList = session
//					.createQuery("from Tournament where host= '" + host + "'").list();
//			
//			return tournamentList;
//		}
//
//
//		public int addTournament(Tournament tournament) {
//			Session session = this.sessionFactory.getCurrentSession();
//			session.persist(tournament);
//			return 1;
//		}
//		
//		public void deleteTournament(String id) {
//			Session session = this.sessionFactory.getCurrentSession();
//			Tournament p = (Tournament) session.load(Tournament.class, new String(id));
//			if (null != p) {
//				session.delete(p);
//			}
//		}
//
//		public int updateTournament(Tournament tournament) {
//			Session session = this.sessionFactory.getCurrentSession();
//			String name = tournament.getName();
//			// get old curr users
//			Tournament p = (Tournament) session.load(Tournament.class, new String(name));
//			
//			if (tournament.getCurrEntrants() !=p.getCurrEntrants()) {
//				throw new StaleStateException("e");
//				
//				
//			}
//			
//			session.persist(tournament);
//			return 1;
//		}
//
//		public int joinTournament(String tournament) {
//			Session session = this.sessionFactory.getCurrentSession();
//			String name = tournament;
//			// get old curr users
//			Tournament p = (Tournament) session.load(Tournament.class, new String(name));
//			
//			if (p.getCurrEntrants() !=p.getCurrEntrants()) {
//				throw new StaleStateException("e");
//				
//				
//			}else if(p.getCurrEntrants() >= p.getMaxEntrants()) {
//				System.out.println((p.getCurrEntrants()));
//				p.setEnterAble("N");
//				return 3;
//				
//				
//			}else {
//				System.out.println("he's in");
//				p.setCurrEntrants(p.currEntrants= p.currEntrants +1);
//			
//			session.persist(p);
//			return 1;
//		}
//		
//		
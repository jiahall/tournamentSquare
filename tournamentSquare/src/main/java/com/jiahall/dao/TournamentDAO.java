package com.jiahall.dao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiahall.model.Tournament;

@Repository
public class TournamentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public List getAllTournaments() {
		Session session = this.sessionFactory.getCurrentSession();
		List tournamentList = session.createQuery("from Tournament WHERE enterAble = 'y' AND currEntrants < maxEntrants").list();
		return tournamentList;
	}

	public List getTournaments(String criteria) {
		Session session = this.sessionFactory.getCurrentSession();
		List tournamentList = session
				.createQuery("from Tournament where ( name LIKE '%" + criteria + "%'"+
		" OR postcode LIKE '%" + criteria + "%'"+" OR game LIKE '%" + criteria + "%') AND NOT enterAble = 'n' AND currEntrants < maxEntrants" ).list();
		
		
		return tournamentList;
	}

	public List getHostedTournaments(String host) {
		Session session = this.sessionFactory.getCurrentSession();
		List tournamentList = session
				.createQuery("from Tournament where host= '" + host + "'").list();
		
		return tournamentList;
	}


	public int addTournament(Tournament tournament) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(tournament);
		return 1;
	}
	
	public void deleteTournament(String id) {
		Session session = this.sessionFactory.getCurrentSession();
		Tournament p = (Tournament) session.load(Tournament.class, new String(id));
		if (null != p) {
			session.delete(p);
		}
	}

	public int updateTournament(Tournament tournament) {
		Session session = this.sessionFactory.getCurrentSession();
		String name = tournament.getName();
		// get old curr users
		Tournament p = (Tournament) session.load(Tournament.class, new String(name));
		
		if (tournament.getCurrEntrants() !=p.getCurrEntrants()) {
			throw new StaleStateException("e");
			
			
		}
		
		session.persist(tournament);
		return 1;
	}
	
	
	
	
	// user joined join

	/*public User addUser(User user) {
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
	}*/

}
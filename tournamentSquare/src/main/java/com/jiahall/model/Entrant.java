package com.jiahall.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


@Entity
@Table(name="TS_Entrants")
@Proxy(lazy = false)
public class Entrant{
	
	@Id
	@Column(name="tournament")
	String tournament;

	@Column(name="username")
	String username; 
	
	@Column(name="placing")
	int placing; 

	@Column(name="seeding")
	int seeding; 
	
	@Column(name="hasApp")
	String hasApp;

	@Column(name="screen")
	int screen; 
	
	@Column(name="console")
	int console; 

	public Entrant() {
		super();
		
	}

	public String getTournament() {
		return tournament;
	}

	public void setUserName(String tournament) {
		this.tournament = tournament;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPlacing() {
		return placing;
	}

	public void setPlacing(int placing) {
		this.placing = placing;
	}

	public int getSeeding() {
		return seeding;
	}

	public void setSeeding(int seeding) {
		this.seeding = seeding;
	}

	public String getHasApp() {
		return hasApp;
	}

	public void setHasApp(String hasApp) {
		this.hasApp = hasApp;
	}

	public int getScreen() {
		return screen;
	}

	public void setScreen(int screen) {
		this.screen = screen;
	}

	public int getConsole() {
		return console;
	}

	public void setConsole(int console) {
		this.console = console;
	}

	public Entrant(String tournament, String username, int placing, int seeding, String hasApp, int screen, int console) {
		super();
		this.tournament = tournament;
		this.username = username;
		this.placing = placing;
		this.seeding = seeding;
		this.hasApp = hasApp;
		this.screen = screen;
		this.console = console;
	}

}
	


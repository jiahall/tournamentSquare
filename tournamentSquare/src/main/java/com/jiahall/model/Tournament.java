package com.jiahall.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Proxy;


@Entity
@Table(name="TS_Tournament")
@Proxy(lazy = false)
public class Tournament {


	@Id
	@Column(name="name")
	String name;
	
	@Column(name="maxEntrants")
	int maxEntrants; 
	
	@Column(name="postcode")
	String postcode; 
	
	@Column(name="enterAble")
	String enterAble; 
	
	@Column(name="game")
	String game;
	
	@Column(name="location")
	String location;
	
	@Version
	@Column(name="currEntrants")
	int currEntrants; 
	
	@Column(name="host")
	String host; 
	
	@Column(name="entryFee")
	int entryFee; 
	
	@Column(name="prize")
	String prize; 
	
	@Column(name="signIn")
	String signIn;
	
	public Tournament(String name, int maxEntrants, String postcode, String location, String enterAble, String game, int currEntrants,
			String host, int entryFee, String prize, String signIn) {
		super();
		this.name = name;
		this.maxEntrants = maxEntrants;
		this.postcode = postcode;
		this.location = location;
		this.enterAble = enterAble;
		this.game = game;
		this.currEntrants = currEntrants;
		this.host = host;
		this.entryFee = entryFee;
		this.prize = prize;
		this.signIn = signIn;
	} 
	public Tournament() {
		super();
	}

	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxEntrants() {
		return maxEntrants;
	}

	public void setMaxEntrants(int maxEntrants) {
		this.maxEntrants = maxEntrants;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getEnterAble() {
		return enterAble;
	}

	public void setEnterAble(String enterAble) {
		this.enterAble = enterAble;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public int getCurrEntrants() {
		return currEntrants;
	}

	public void setCurrEntrants(int currEntrants) {
		this.currEntrants = currEntrants;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getEntryFee() {
		return entryFee;
	}

	public void setEntryFee(int entryFee) {
		this.entryFee = entryFee;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}

	public String getSignIn() {
		return signIn;
	}

	public void setSignIn(String signIn) {
		this.signIn =signIn;
	}	
}
package com.jiahall.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;


@Entity
@Table(name="TS_Users")
@Proxy(lazy = false)
public class User{


	@Id
	@Column(name="userName")
	String userName;

	@Column(name="email")
	String email; 

	@Column(name="verified")
	String verified;

	@Column(name="passWord")
	String passWord;

	public User() {
		super();
	}


	public User(String userName, String email, String verified, String passWord) {
		super();
		this.userName = userName;
		this.email = email;
		this.verified = verified;
		this.passWord = passWord;
	}
	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

}
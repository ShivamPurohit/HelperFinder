package com.major.one.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;

@Entity
public class User {
	
	
	@Id
    @Column
    @GeneratedValue
	private int uniqueNO;
	
	private String username;
	private String email;
	private String pass;

	
	User(){}
	
	public int getUniqueNO() {
		return uniqueNO;
	}
	public void setUniqueNO(int uniqueNO) {
		this.uniqueNO = uniqueNO;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	
	public User(String username, String email, String pass) {
		super();
		this.username = username;
		this.email = email;
		this.pass = pass;
	
	}
	@Override
	public String toString() {
		return "User [uniqueNO=" + uniqueNO + ", username=" + username + ", email=" + email + ", pass=" + pass + "]";
	}
	
	
	
}

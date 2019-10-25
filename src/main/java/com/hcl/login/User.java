package com.hcl.login;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	
	@Id
	private String userName;
	private String name;
	private String sex;
	private String dob;
	private String password;
	
	
	
	



	protected  User() {
		
	}


	public User(String userName, String name, String sex, String dob, String password) {
		super();
		this.userName = userName;
		this.name = name;
		this.sex = sex;
		this.dob = dob;
		this.password = password;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}

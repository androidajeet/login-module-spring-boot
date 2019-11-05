package com.hcl.login;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Entity for user
 * 
 * @author AjeetY
 *
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	private String userName;
	@Size(min = 2 ,message="name should be atleast 2 charecters")
	private String name;
	@Size(min = 1, message="sex should be M/F/O")
	private String sex;
	private String dob;
	@Size(min = 5, message="password should be at least 5 charecters")
	private String password;

	protected User() {

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

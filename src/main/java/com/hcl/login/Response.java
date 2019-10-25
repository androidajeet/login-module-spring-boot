package com.hcl.login;

import org.springframework.stereotype.Component;

@Component
public class Response {

	private String status;
	private String loginUrl;
	private String isAuthenticated;
	private String isUpdated;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public String getIsAuthenticated() {
		return isAuthenticated;
	}
	public void setIsAuthenticated(String isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	public String getIsUpdated() {
		return isUpdated;
	}
	public void setIsUpdated(String isUpdated) {
		this.isUpdated = isUpdated;
	}
	
	
	
	
}

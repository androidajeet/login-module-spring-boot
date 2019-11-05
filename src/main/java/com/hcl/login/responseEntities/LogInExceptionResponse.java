package com.hcl.login.responseEntities;


/**
 * Response class for showing Exceptions while Log in
 * @author AjeetY
 *
 */
public class LogInExceptionResponse {

	private String isAuthenticated;

	public LogInExceptionResponse(String isAuthenticated) {
		super();
		this.isAuthenticated = isAuthenticated;
	}

	public String getIsAuthenticated() {
		return isAuthenticated;
	}
	
	
	
}

package com.hcl.login.responseEntities;


/**
 * Response class for showing Response to client while Log in
 * @author AjeetY
 *
 */
import org.springframework.stereotype.Component;

@Component 
public class LoginSuccessResponse {

	private String isAuthenticated;

	public String getIsAuthenticated() {
		return isAuthenticated;
	}

	public void setIsAuthenticated(String isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	
	
	
}

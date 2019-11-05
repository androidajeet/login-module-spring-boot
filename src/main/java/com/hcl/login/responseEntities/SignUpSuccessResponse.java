package com.hcl.login.responseEntities;

import org.springframework.stereotype.Component;

/**
 * Class used for response to client.
 * @author AjeetY
 *
 */
@Component 
public class SignUpSuccessResponse {

	private String status;
	
	private String loginUrl;
	
	
	/**
	 * get the status of request
	 * @return success/failed
	 */
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * get the log in url for user
	 * @return login url 
	 */
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	
	
	
	
	
	
}

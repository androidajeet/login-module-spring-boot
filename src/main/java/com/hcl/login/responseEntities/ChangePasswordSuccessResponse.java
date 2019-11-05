package com.hcl.login.responseEntities;


/**
 * Response class for showing Response to client while changing password
 * @author AjeetY
 *
 */
import org.springframework.stereotype.Component;

@Component 
public class ChangePasswordSuccessResponse {

	private String isUpdated;
	
	
	public String getIsUpdated() {
		return isUpdated;
	}


	public void setIsUpdated(String isUpdated) {
		this.isUpdated = isUpdated;
	}
	
	
	
	
}

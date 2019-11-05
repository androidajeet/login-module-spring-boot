package com.hcl.login.responseEntities;

/**
 * Response class for showing Exceptions while changing password
 * @author AjeetY
 *
 */
public class ChangePasswordExceptionResponse {

	private String isUpdated;

	public ChangePasswordExceptionResponse(String isUpdated) {
		super();
		this.isUpdated = isUpdated;
	}

	public String getIsUpdated() {
		return isUpdated;
	}
	
	
	
}

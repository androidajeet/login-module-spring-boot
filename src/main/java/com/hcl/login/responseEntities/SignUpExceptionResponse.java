package com.hcl.login.responseEntities;


/**
 * Response class for showing Exceptions while Signup
 * @author AjeetY
 *
 */
public class SignUpExceptionResponse {

	private String status;

	public SignUpExceptionResponse(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	
	
	
}

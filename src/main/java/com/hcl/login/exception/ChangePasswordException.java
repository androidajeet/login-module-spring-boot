package com.hcl.login.exception;


/**
 * Custom exception class for handling exception while changing password
 * @author AjeetY
 *
 */
public class ChangePasswordException extends RuntimeException {

	
	public ChangePasswordException(String massage) {
		super(massage);

	}

}

package com.hcl.login.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hcl.login.responseEntities.ChangePasswordExceptionResponse;
import com.hcl.login.responseEntities.ExceptionResponse;
import com.hcl.login.responseEntities.LogInExceptionResponse;
import com.hcl.login.responseEntities.SignUpExceptionResponse;

/**
 * This class is used for showing response to client on the basis of Exception
 * class.
 * 
 * @author AjeetY
 *
 */

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * handle all Exceptions
	 * 
	 * @param ex      Exception
	 * @param request WebRequest
	 * @return ResponseEntity
	 * @throws Exception
	 */

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * handle all ChangePasswordException
	 * 
	 * @param ex      Exception
	 * @param request WebRequest
	 * @return ResponseEntity
	 * @throws Exception
	 */
	@ExceptionHandler(ChangePasswordException.class)
	public final ResponseEntity<Object> handleChangePasswordException(Exception ex, WebRequest request)
			throws Exception {

		ChangePasswordExceptionResponse exceptionResponse = new ChangePasswordExceptionResponse(ex.getMessage());

		return new ResponseEntity(exceptionResponse, HttpStatus.FORBIDDEN);
	}

	/**
	 * handle all LogInUserException
	 * 
	 * @param ex      Exception
	 * @param request WebRequest
	 * @return ResponseEntity
	 * @throws Exception
	 */
	@ExceptionHandler(LogInUserException.class)
	public final ResponseEntity<Object> handleLogInException(Exception ex, WebRequest request) throws Exception {

		LogInExceptionResponse exceptionResponse = new LogInExceptionResponse(ex.getMessage());

		return new ResponseEntity(exceptionResponse, HttpStatus.FORBIDDEN);
	}

	/**
	 * handle all SignUpUserException
	 * 
	 * @param ex      Exception
	 * @param request WebRequest
	 * @return ResponseEntity
	 * @throws Exception
	 */
	@ExceptionHandler(SignUpUserException.class)
	public final ResponseEntity<Object> handleSignUpException(Exception ex, WebRequest request) throws Exception {

		SignUpExceptionResponse exceptionResponse = new SignUpExceptionResponse(ex.getMessage());

		return new ResponseEntity(exceptionResponse, HttpStatus.FORBIDDEN);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation failed",
				ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}

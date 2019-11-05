package com.hcl.login.responseEntities;


/**
 * Response class for showing all common Exceptions 
 * @author AjeetY
 *
 */
import java.util.Date;

public class ExceptionResponse {
	private Date timestamp;
	private String massage;
	private String detail;
	
	public ExceptionResponse(Date timestamp, String massage, String detail) {
		super();
		this.timestamp = timestamp;
		this.massage = massage;
		this.detail = detail; 
	}
	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getMassage() {
		return massage;
	}
	
	public String getDetail() {
		return detail;
	}
	

	
	
}

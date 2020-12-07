package com.challenge.cities.exception;

public class ServiceException extends Exception {
	String message;
	String details;
	
	private static final long serialVersionUID = 1L;

	
	public ServiceException() {
		super();
	}
	
	public ServiceException(String msg){
		this.message=msg;
	}
	
	public ServiceException(String msg,String details){
		this.message=msg;
		this.details= details;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
	
}

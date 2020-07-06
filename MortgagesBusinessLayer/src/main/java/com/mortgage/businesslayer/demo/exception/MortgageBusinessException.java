package com.mortgage.businesslayer.demo.exception;

import org.springframework.http.HttpStatus;

public class MortgageBusinessException extends Exception {

	private static final long serialVersionUID = 143320424902L;

	private HttpStatus status;

	public MortgageBusinessException(String message) {
		super(message);
	}

	public MortgageBusinessException(String message, Throwable t) {
		super(message, t);
	}
	
	public MortgageBusinessException(String message, Throwable t,HttpStatus status) {
		super(message, t);
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}

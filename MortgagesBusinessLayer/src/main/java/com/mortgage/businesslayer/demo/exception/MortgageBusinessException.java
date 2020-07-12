package com.mortgage.businesslayer.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class to catch exception scenario at every layer in pplication
 * @author bhagyesh
 *
 */
public class MortgageBusinessException extends Exception {

	private static final long serialVersionUID = 143320424902L;

	/**
	 * HTTP Status
	 */
	private HttpStatus status;

	/**
	 * constructor for Setting up exception message 
	 * @param message
	 */
	public MortgageBusinessException(final String message) {
		super(message);
	}
	/**
	 * constructor for Setting up exception message and error instance
	 * @param message
	 */
	public MortgageBusinessException(final String message, final Throwable t) {
		super(message, t);
	}
	
	public MortgageBusinessException(final String message, final Throwable t, final HttpStatus status) {
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

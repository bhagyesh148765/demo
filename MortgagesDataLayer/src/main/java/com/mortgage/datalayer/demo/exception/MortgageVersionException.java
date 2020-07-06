package com.mortgage.datalayer.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MortgageVersionException extends Exception {

	private static final long serialVersionUID = 1L;

	public MortgageVersionException(String message) {
		super(message);
	}

	public MortgageVersionException(String message, Throwable t) {
		super(message, t);
	}
}

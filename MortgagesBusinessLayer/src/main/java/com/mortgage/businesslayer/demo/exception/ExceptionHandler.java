package com.mortgage.businesslayer.demo.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
/**
 * AOP class to handle application exceptions 
 * @author bhagyesh
 *
 */
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	private static final String INVALID_INPUT_REQUEST = "InvalidInputRequest";
	private static final String MORTGAGE_ERROR = "Mortgage Error";

	@Override
	/**
	 * this method handles MethodArgumentNotValid exception
	 * 
	 * @param MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus
	 *        status, WebRequest request
	 * @output ResponseEntity
	 */
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), message, INVALID_INPUT_REQUEST);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	/**
	 * this method handles MortgageCustomException exception
	 * 
	 * @param MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus
	 *        status, WebRequest request
	 * @output ResponseEntity
	 */
	public ResponseEntity<Object> handleMortgageBusinessException(MortgageBusinessException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), MORTGAGE_ERROR);
		return new ResponseEntity<>(errorDetails, ex.getStatus());
	}

	/*
	 * @Override public ResponseEntity<Object>
	 * handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders
	 * headers, HttpStatus status, WebRequest request) { ErrorDetails errorDetails =
	 * new ErrorDetails(new Date(), ex.getMessage(), "InvalidInputRequest"); return
	 * new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST); }
	 */
}
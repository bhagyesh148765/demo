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
public class ExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		ErrorDetails errorDetails = new ErrorDetails(new Date(), message, "InvalidInputRequest");
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<Object> handleMortgageBusinessException(MortgageBusinessException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), "eror");
		return new ResponseEntity<>(errorDetails, ex.getStatus());
	}


}
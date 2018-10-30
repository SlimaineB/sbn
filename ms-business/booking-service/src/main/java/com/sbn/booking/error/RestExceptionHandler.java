package com.sbn.booking.error;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value = { MethodArgumentNotValidException.class })
	public ResponseEntity<ErrorMessage> MethodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest request) {

		ErrorMessage errorObj = new ErrorMessage(new Date(),
				"Arguments non valide : " + ex.getBindingResult().getAllErrors().stream()
						.map(elm -> elm.getDefaultMessage()).collect(Collectors.joining(",")),
				request.getDescription(false));
		return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(value = { AmpsRequestNotFoundException.class })
	public ResponseEntity<ErrorMessage> handleAmpsRequestNotFoundException(AmpsRequestNotFoundException ex,
			WebRequest request) {

		ErrorMessage errorObj = new ErrorMessage(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorObj, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	/*
	 * @ExceptionHandler(value = { Exception.class }) public
	 * ResponseEntity<ErrorMessage> handleAnyException(Exception ex, WebRequest
	 * request) { ErrorMessage errorObj = new ErrorMessage(new Date(),
	 * ex.getMessage(), request.getDescription(false)); return new
	 * ResponseEntity<>(errorObj, new HttpHeaders(),
	 * HttpStatus.INTERNAL_SERVER_ERROR); }
	 */

}
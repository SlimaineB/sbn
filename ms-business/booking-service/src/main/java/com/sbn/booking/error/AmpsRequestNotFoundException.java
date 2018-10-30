package com.sbn.booking.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AmpsRequestNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4091739274616933406L;
	
	public AmpsRequestNotFoundException(String message){
		super(message);
	}
}
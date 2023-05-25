package com.allanborges.restaurantAPI.services.exceptions;

public class InsufficientMenuQuantityException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InsufficientMenuQuantityException(String message, Throwable cause) {
		super(message, cause);
	}

	public InsufficientMenuQuantityException(String message) {
		super(message);
	}
	
}

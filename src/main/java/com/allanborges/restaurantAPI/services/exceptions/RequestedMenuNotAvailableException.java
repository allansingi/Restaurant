package com.allanborges.restaurantAPI.services.exceptions;

public class RequestedMenuNotAvailableException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RequestedMenuNotAvailableException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestedMenuNotAvailableException(String message) {
		super(message);
	}
	
}

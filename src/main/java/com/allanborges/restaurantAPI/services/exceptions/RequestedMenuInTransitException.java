package com.allanborges.restaurantAPI.services.exceptions;

public class RequestedMenuInTransitException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RequestedMenuInTransitException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestedMenuInTransitException(String message) {
		super(message);
	}
	
}

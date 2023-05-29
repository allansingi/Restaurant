package com.allanborges.restaurantAPI.services.exceptions;

public class RequestStatusNotUpdatableException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RequestStatusNotUpdatableException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestStatusNotUpdatableException(String message) {
		super(message);
	}
	
}

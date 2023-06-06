package com.allanborges.restaurantAPI.domain.enums;

/*
 * Request Status ENUM for Request logic attribution
 */
public enum RequestStatus {
	
	ORDER_RECEIVED(0, "ORDER_RECEIVED"), PREPARING(1, "PREPARING"), READY(2, "READY"), IN_TRANSIT(3,"IN_TRANSIT"), DELIVERED(4, "DELIVERED"), CANCELLED(5, "CANCELLED");
	
	private Integer code;
	private String description;
	
	private RequestStatus(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static RequestStatus toEnum(Integer code) {
		if(code == null)
			return null;
		
		for(RequestStatus x : RequestStatus.values()) {
			if(code.equals(x.getCode()))
				return x;
		}
		throw new IllegalArgumentException("Invalid Status");
	}

}
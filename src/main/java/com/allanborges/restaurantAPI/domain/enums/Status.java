package com.allanborges.restaurantAPI.domain.enums;

public enum Status {
	
	ORDER_RECEIVED(0, "ORDER_RECEIVED"), PREPARING(1, "PREPARING"), READY(2, "READY"), IN_TRANSIT(3,"IN_TRANSIT"), DELIVERED(4, "DELIVERED"), CANCELLED(5, "CANCELLED");
	
	private Integer code;
	private String description;
	
	private Status(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Status toEnum(Integer code) {
		if(code == null)
			return null;
		
		for(Status x : Status.values()) {
			if(code.equals(x.getCode()))
				return x;
		}
		throw new IllegalArgumentException("Invalid Status");
	}

}
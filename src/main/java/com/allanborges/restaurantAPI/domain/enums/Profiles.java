package com.allanborges.restaurantAPI.domain.enums;

public enum Profiles {
	
	ADMIN(0, "ROLE_ADMIN"), CLIENT(1, "ROLE_CLIENT"), COURIER(2, "ROLE_COURIER");
	
	private Integer code;
	private String description;
	
	private Profiles(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static Profiles toEnum(Integer code) {
		if(code == null)
			return null;
		
		for(Profiles x : Profiles.values()) {
			if(code.equals(x.getCode()))
				return x;
		}
		throw new IllegalArgumentException("Invalid Profile");
	}

}
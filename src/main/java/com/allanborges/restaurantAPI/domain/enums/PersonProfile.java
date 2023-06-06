package com.allanborges.restaurantAPI.domain.enums;

/*
 * Person Profiles ENUM for Authorization logic attribution
 */
public enum PersonProfile {
	
	ADMIN(0, "ROLE_ADMIN"), CLIENT(1, "ROLE_CLIENT"), COURIER(2, "ROLE_COURIER");
	
	private Integer code;
	private String description;
	
	private PersonProfile(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	public static PersonProfile toEnum(Integer code) {
		if(code == null)
			return null;
		
		for(PersonProfile x : PersonProfile.values()) {
			if(code.equals(x.getCode()))
				return x;
		}
		throw new IllegalArgumentException("Invalid Profile");
	}

}
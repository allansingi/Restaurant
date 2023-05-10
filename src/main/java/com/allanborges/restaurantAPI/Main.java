package com.allanborges.restaurantAPI;

import com.allanborges.restaurantAPI.util.DateGenerator;

public class Main {

	public static void main(String[] args) {

		DateGenerator dateGenerator = new DateGenerator();
		
		System.out.println(dateGenerator.generateCurrentDate());
		
	}

}

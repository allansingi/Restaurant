package com.allanborges.restaurantAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.allanborges.restaurantAPI.services.DBService;

@Configuration
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	void instantiateDB() {
		this.dbService.instantiateDB();
	}

}

package com.allanborges.restaurantAPI.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateGenerator {
	
	public String generateCurrentDate() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = dateFormat.format(date);
		return formattedDate;
	}
	
	public LocalDateTime generateCurrentDateTime() {
	    String currentDateTime = generateCurrentDate();
	    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    LocalDateTime localDateTime = LocalDateTime.parse(currentDateTime,dateTimeFormatter);
	    return localDateTime;
	}
	
	
	

}
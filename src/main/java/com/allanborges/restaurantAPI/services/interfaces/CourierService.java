package com.allanborges.restaurantAPI.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allanborges.restaurantAPI.domain.Courier;
import com.allanborges.restaurantAPI.domain.dtos.CourierDTO;

@Service
public interface CourierService {
	
	List<Courier> getAllCourier();
	Courier getCourierById(Integer id);
	Courier addCourier(CourierDTO courierDTO);
	Courier updateCourier(CourierDTO courierDTO);
	void deleteCourier(Integer id);

}
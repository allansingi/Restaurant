package com.allanborges.restaurantAPI.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allanborges.restaurantAPI.domain.Request;
import com.allanborges.restaurantAPI.domain.dtos.RequestDTO;

@Service
public interface RequestService {

	List<Request> getAllRequests();
	List<Request> getReadyRequests();
	Request getRequestById(Request request);
	Request createRequest(RequestDTO requestDTO);
	
	
	
}
package com.allanborges.restaurantAPI.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allanborges.restaurantAPI.domain.Request;
import com.allanborges.restaurantAPI.domain.dtos.RequestDTO;

/*
 * Methods for controller layer end-points usage
 */
@Service
public interface RequestService {

	List<Request> getAllRequests();
	List<Request> getReadyRequests();
	Request getRequestById(Integer requestId);
	Request createRequest(RequestDTO requestDTO);
	Request updateRequest(RequestDTO requestDTO);
	
}
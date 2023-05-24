package com.allanborges.restaurantAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allanborges.restaurantAPI.domain.Request;
import com.allanborges.restaurantAPI.domain.dtos.RequestDTO;
import com.allanborges.restaurantAPI.repositories.RequestRepository;
import com.allanborges.restaurantAPI.services.exceptions.ObjectNotFoundException;
import com.allanborges.restaurantAPI.services.interfaces.RequestService;

@Service
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	private RequestRepository requestRepository;

	@Override
	public List<Request> getAllRequests() {
		return requestRepository.findAll();
	}
	
	@Override
	public List<Request> getReadyRequests() {
		return requestRepository.findReadyRequests();
	}

	@Override
	public Request getRequestById(Request request) {
		Optional<Request> obj = requestRepository.findById(request.getId());
		return obj.orElseThrow(() -> new ObjectNotFoundException("Request with id " + request.getId() + " not found"));
	}

	@Override
	public Request createRequest(RequestDTO requestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
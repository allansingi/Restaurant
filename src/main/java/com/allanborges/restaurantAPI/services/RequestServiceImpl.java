package com.allanborges.restaurantAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allanborges.restaurantAPI.domain.Client;
import com.allanborges.restaurantAPI.domain.Courier;
import com.allanborges.restaurantAPI.domain.Menu;
import com.allanborges.restaurantAPI.domain.Request;
import com.allanborges.restaurantAPI.domain.dtos.RequestDTO;
import com.allanborges.restaurantAPI.domain.enums.RequestStatus;
import com.allanborges.restaurantAPI.repositories.ClientRepository;
import com.allanborges.restaurantAPI.repositories.CourierRepository;
import com.allanborges.restaurantAPI.repositories.MenuRepository;
import com.allanborges.restaurantAPI.repositories.RequestRepository;
import com.allanborges.restaurantAPI.services.exceptions.MethodArgumentNotValidException;
import com.allanborges.restaurantAPI.services.exceptions.ObjectNotFoundException;
import com.allanborges.restaurantAPI.services.interfaces.RequestService;

@Service
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private CourierRepository courierRepository;
	
	@Autowired
	private MenuRepository menuRepository;

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
		return requestRepository.save(newRequest(requestDTO));
	}
	
	
	private Request newRequest(RequestDTO requestDTO) {
		Optional<Client> client = clientRepository.findById(requestDTO.getClientId());
		Optional<Courier> courier = courierRepository.findById(6);
		Optional<Menu> menu = menuRepository.findById(requestDTO.getRequestedMenuId());
		
		Request request = new Request();
		
		request.setClient(client.get());
		request.setDeliveryAddress(client.get().getAddress());
		request.setRequestedMenuId(menu.get().getId());
		request.setRequestedMenuName(menu.get().getName());
		if(requestDTO.getRequestedQuantity() == null)
			throw new MethodArgumentNotValidException("Field RequestedQuantity is MANDATORY!");
		request.setRequestedQuantity(requestDTO.getRequestedQuantity());
		request.setRequestStatus(RequestStatus.ORDER_RECEIVED);
		request.setCourier(courier.get());
		
		return request;
	}

}
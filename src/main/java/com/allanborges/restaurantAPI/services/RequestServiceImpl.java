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
import com.allanborges.restaurantAPI.repositories.MenuRepository;
import com.allanborges.restaurantAPI.repositories.RequestRepository;
import com.allanborges.restaurantAPI.services.exceptions.InsufficientMenuQuantityException;
import com.allanborges.restaurantAPI.services.exceptions.MethodArgumentNotValidException;
import com.allanborges.restaurantAPI.services.exceptions.ObjectNotFoundException;
import com.allanborges.restaurantAPI.services.exceptions.RequestedMenuNotAvailableException;
import com.allanborges.restaurantAPI.services.interfaces.ClientService;
import com.allanborges.restaurantAPI.services.interfaces.CourierService;
import com.allanborges.restaurantAPI.services.interfaces.MenuService;
import com.allanborges.restaurantAPI.services.interfaces.RequestService;

@Service
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private CourierService courierService;
	
	@Autowired
	private MenuService menuService;
	
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
	public Request getRequestById(Integer requestId) {
		Optional<Request> obj = requestRepository.findById(requestId);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Request with id " + requestId + " not found"));
	}

	@Override
	public Request createRequest(RequestDTO requestDTO) {
		Client client = clientService.getClientById(requestDTO.getClientId());
		Courier courier = courierService.getCourierById(6);
		
		List<Menu> activeMenuList = menuService.getActiveMenu();
		Menu requestedMenu = menuService.getMenuById(requestDTO.getRequestedMenuId());
		if(!activeMenuList.contains(requestedMenu))
			throw new RequestedMenuNotAvailableException("The requested menu is not available");
		
		Request request = new Request();
		
		request.setClient(client);
		request.setDeliveryAddress(client.getAddress());
		
		request.setRequestedMenuId(requestedMenu.getId());
		request.setRequestedMenuName(requestedMenu.getName());
		if(requestDTO.getRequestedQuantity() == null)
			throw new MethodArgumentNotValidException("Field RequestedQuantity is MANDATORY!");
		else if(requestDTO.getRequestedQuantity() > requestedMenu.getQuantity() || requestedMenu.getQuantity() == 0)
			throw new InsufficientMenuQuantityException("Insufficient menu availability, there are " + requestedMenu.getQuantity() + " left");
		requestedMenu.setQuantity(requestedMenu.getQuantity() - requestDTO.getRequestedQuantity());
		menuRepository.save(requestedMenu);
		
		request.setRequestedQuantity(requestDTO.getRequestedQuantity());
		request.setRequestStatus(RequestStatus.ORDER_RECEIVED);
		request.setCourier(courier);
		
		return requestRepository.save(request);
	}


}
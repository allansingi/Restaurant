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
import com.allanborges.restaurantAPI.services.exceptions.RequestStatusNotUpdatableException;
import com.allanborges.restaurantAPI.services.exceptions.RequestedMenuInTransitException;
import com.allanborges.restaurantAPI.services.exceptions.RequestedMenuNotAvailableException;
import com.allanborges.restaurantAPI.services.interfaces.ClientService;
import com.allanborges.restaurantAPI.services.interfaces.CourierService;
import com.allanborges.restaurantAPI.services.interfaces.MenuService;
import com.allanborges.restaurantAPI.services.interfaces.RequestService;
import com.allanborges.restaurantAPI.util.DateGenerator;

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
	@Autowired
	private DateGenerator dateGenerator;
	

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
		Request request = new Request();
		
		//clientId Field
		if(requestDTO.getClientId() == null)
			throw new MethodArgumentNotValidException("Field clientId is MANDATORY!");
		else {
			Client client = clientService.getClientById(requestDTO.getClientId());
			request.setClient(client);
			request.setDeliveryAddress(client.getAddress());
		}
		
		//requestedMenuId requestedQuantity Fields
		if(requestDTO.getRequestedMenuId() == null || requestDTO.getRequestedQuantity() == null)
			throw new MethodArgumentNotValidException("Fields requestedMenuId and requestedQuantity are MANDATORY!");
		else {
			//requestedMenuId Field
			List<Menu> activeMenuList = menuService.getActiveMenu();
			Menu requestedMenu = menuService.getMenuById(requestDTO.getRequestedMenuId());
			if(!activeMenuList.contains(requestedMenu))
				throw new RequestedMenuNotAvailableException("The requested menu is not available");
			request.setRequestedMenuId(requestedMenu.getId());
			request.setRequestedMenuName(requestedMenu.getName());
		
			//RequestedQuantity Field
			if(requestDTO.getRequestedQuantity() == null)
				throw new MethodArgumentNotValidException("Field RequestedQuantity is MANDATORY!");
			else if(requestDTO.getRequestedQuantity() == 0)
				throw new InsufficientMenuQuantityException("Cannot place a request with quantity 0");
			else if(requestDTO.getRequestedQuantity() > requestedMenu.getQuantity())
				throw new InsufficientMenuQuantityException("Insufficient menu availability, there are " + requestedMenu.getQuantity() + " available");
			
			//MenuQuantity Table Field Update
			requestedMenu.setQuantity(requestedMenu.getQuantity() - requestDTO.getRequestedQuantity());
			menuRepository.save(requestedMenu);
		}
		request.setRequestedQuantity(requestDTO.getRequestedQuantity());
		request.setRequestStatus(RequestStatus.ORDER_RECEIVED);
		request.setCreateDate(dateGenerator.generateCurrentDateTime());
		
		//Default courier attribution to prevent null data and request attribution afterwards
		Courier courier = courierService.getCourierById(6);
		request.setCourier(courier);
		
		return requestRepository.save(request);
	}

	@Override
	public Request updateRequest(RequestDTO requestDTO) {
		Request currentRequest = getRequestById(requestDTO.getId());
		Request request = new Request();
		request.setCreateDate(currentRequest.getCreateDate());
		request.setId(requestDTO.getId());
		request.setRequestedMenuName(currentRequest.getRequestedMenuName());
		
		//ClientID Field
		if(requestDTO.getClientId() == null) {
			request.setClient(currentRequest.getClient());
			request.setDeliveryAddress(currentRequest.getDeliveryAddress());
		}else if(canUpdateDueToRequestedStatus(currentRequest.getRequestStatus().getCode())) {
			Client client = clientService.getClientById(requestDTO.getClientId());
			request.setClient(client);
			request.setDeliveryAddress(client.getAddress());
			request.setUpdateDate(dateGenerator.generateCurrentDateTime());
		}
		
		//MenuId and RequestedQuantity fields
		if(requestDTO.getRequestedMenuId() == null && requestDTO.getRequestedQuantity() == null) {
			request.setRequestedMenuId(currentRequest.getRequestedMenuId());
			request.setRequestedQuantity(currentRequest.getRequestedQuantity());
		}else if(requestDTO.getRequestedMenuId() != null && requestDTO.getRequestedQuantity() == null)
			throw new ObjectNotFoundException("In order to change the requestedMenu, you must inform the Quantity");
		else if(requestDTO.getRequestedMenuId() == null && requestDTO.getRequestedQuantity() != null) {
			if(canUpdateDueToRequestedStatus(currentRequest.getRequestStatus().getCode())) {
				Menu currentRequestedMenu = menuService.getMenuById(currentRequest.getRequestedMenuId());
				if(requestDTO.getRequestedQuantity() == 0)
					throw new InsufficientMenuQuantityException("Cannot place a request with quantity 0");
				else if((currentRequestedMenu.getQuantity() + currentRequest.getRequestedQuantity()) < requestDTO.getRequestedQuantity())
					throw new InsufficientMenuQuantityException("Insufficient menu availability, there are " + currentRequestedMenu.getQuantity() + " available");
				else {
					//MenuQuantity Table Field Update
					currentRequestedMenu.setQuantity((currentRequestedMenu.getQuantity() + currentRequest.getRequestedQuantity()) - requestDTO.getRequestedQuantity());
					menuRepository.save(currentRequestedMenu);
					
					request.setRequestedMenuId(currentRequestedMenu.getId());
					request.setRequestedMenuName(currentRequestedMenu.getName());
					request.setRequestedQuantity(requestDTO.getRequestedQuantity());
					request.setRequestStatus(currentRequest.getRequestStatus());
					request.setUpdateDate(dateGenerator.generateCurrentDateTime());
				}
			}
		}else if(requestDTO.getRequestedMenuId() != null && requestDTO.getRequestedQuantity() != null) {
			if(canUpdateDueToRequestedStatus(currentRequest.getRequestStatus().getCode())) {
				Menu currentRequestedMenu = menuService.getMenuById(currentRequest.getRequestedMenuId());
				List<Menu> activeMenuList = menuService.getActiveMenu();
				Menu newRequestedMenu = menuService.getMenuById(requestDTO.getRequestedMenuId());
				if(!activeMenuList.contains(newRequestedMenu))
					throw new RequestedMenuNotAvailableException("The requested menu is not available in the activeList");
				else if(requestDTO.getRequestedQuantity() == 0)
					throw new InsufficientMenuQuantityException("Cannot place a request with quantity 0");
				else if(requestDTO.getRequestedQuantity() > newRequestedMenu.getQuantity())
					throw new InsufficientMenuQuantityException("Insufficient menu availability, there are " + newRequestedMenu.getQuantity() + " available");
				else {
					//Returning current menuQuantity
					currentRequestedMenu.setQuantity(currentRequestedMenu.getQuantity() + currentRequest.getRequestedQuantity());
					menuRepository.save(currentRequestedMenu);
					//NewRequestedMenu - MenuQuantity Table Field Update
					newRequestedMenu.setQuantity(newRequestedMenu.getQuantity() - requestDTO.getRequestedQuantity());
					menuRepository.save(newRequestedMenu);
					
					request.setRequestedMenuId(newRequestedMenu.getId());
					request.setRequestedMenuName(newRequestedMenu.getName());
					request.setRequestedQuantity(requestDTO.getRequestedQuantity());
					request.setRequestStatus(currentRequest.getRequestStatus());
					request.setUpdateDate(dateGenerator.generateCurrentDateTime());
				}
			}
		}
		
		//RequestStatus Field
		if(requestDTO.getRequestStatus() == null)
			request.setRequestStatus(currentRequest.getRequestStatus());
		else {
			if(currentRequest.getRequestStatus() == RequestStatus.DELIVERED)
				throw new RequestStatusNotUpdatableException("The Request was already DELIVERED and cannot change status");
			else if(currentRequest.getRequestStatus() == RequestStatus.CANCELLED)
				throw new RequestStatusNotUpdatableException("The Request was already CANCELLED and cannot change status");
			else if(requestDTO.getRequestStatus() == 4) {
				request.setRequestStatus(RequestStatus.toEnum(requestDTO.getRequestStatus()));
				request.setUpdateDate(currentRequest.getUpdateDate());
				request.setDeliveredDate(dateGenerator.generateCurrentDateTime());
				}
			else if(requestDTO.getRequestStatus() == 5) {
				Menu requestedMenu = menuService.getMenuById(currentRequest.getRequestedMenuId());
				requestedMenu.setQuantity(requestedMenu.getQuantity() + currentRequest.getRequestedQuantity());
				menuRepository.save(requestedMenu);
				request.setRequestStatus(RequestStatus.toEnum(requestDTO.getRequestStatus()));
				request.setUpdateDate(dateGenerator.generateCurrentDateTime());
			} else {
				request.setRequestStatus(RequestStatus.toEnum(requestDTO.getRequestStatus()));
				request.setUpdateDate(dateGenerator.generateCurrentDateTime());
			}
		}
		
		//CourierID Field
		if(requestDTO.getCourierId() == null)
			request.setCourier(currentRequest.getCourier());
		else if(currentRequest.getRequestStatus() != RequestStatus.READY)
			throw new RequestStatusNotUpdatableException("The Courier only can be assigned to requests with READY status");
		else {
			Courier courier = courierService.getCourierById(requestDTO.getCourierId());
			request.setCourier(courier);
			request.setUpdateDate(dateGenerator.generateCurrentDateTime());
		}
		return requestRepository.save(request);
	}
	
	
	private boolean canUpdateDueToRequestedStatus(Integer currentRequestStatusCode) {
		if(currentRequestStatusCode == 3)
			throw new RequestedMenuInTransitException("The Request status is IN_TRANSIT and cannot be altered");
		else if(currentRequestStatusCode == 4)
			throw new RequestedMenuInTransitException("The Requestet was already DELIVERED and cannot be altered");
		else if(currentRequestStatusCode == 5)
			throw new RequestedMenuInTransitException("The Requested was CANCELLED and cannot be altered");
		return true;
	}
}
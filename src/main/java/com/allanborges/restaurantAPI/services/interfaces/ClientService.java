package com.allanborges.restaurantAPI.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allanborges.restaurantAPI.domain.Client;
import com.allanborges.restaurantAPI.domain.dtos.ClientDTO;

/*
 * Methods for controller layer end-points usage
 */
@Service
public interface ClientService {

	List<Client> getAllClient();
	Client getClientById(Integer id);
	Client addClient(ClientDTO clientDTO);
	Client updateClient(ClientDTO clientDTO);
	void deleteClient(Integer id);
	
}
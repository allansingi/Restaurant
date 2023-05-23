package com.allanborges.restaurantAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allanborges.restaurantAPI.domain.Client;
import com.allanborges.restaurantAPI.domain.Person;
import com.allanborges.restaurantAPI.domain.dtos.ClientDTO;
import com.allanborges.restaurantAPI.repositories.ClientRepository;
import com.allanborges.restaurantAPI.repositories.PersonRepository;
import com.allanborges.restaurantAPI.services.exceptions.DataIntegrityViolationException;
import com.allanborges.restaurantAPI.services.exceptions.MethodArgumentNotValidException;
import com.allanborges.restaurantAPI.services.exceptions.ObjectNotFoundException;
import com.allanborges.restaurantAPI.services.interfaces.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Client> getAllClient() {
		return clientRepository.findAll();
	}

	@Override
	public Client getClientById(Integer id) {
		try {
			Optional<Client> client = clientRepository.findById(id);
			return client.get();
		} catch (Exception e) {
			throw new ObjectNotFoundException("Client with id " + id + " not found");
		}
	}

	@Override
	public Client addClient(ClientDTO clientDTO) {
		clientDTO.setId(null);
		if(clientDTO.getName() == null || clientDTO.getNif() == null || clientDTO.getAddress() == null || clientDTO.getEmail() == null || clientDTO.getPassword() == null)
			throw new MethodArgumentNotValidException("Fields NAME, NIF, ADDRESS, EMAIL and PASSWORD are mandatory");
		else {
			validateNifEmailAndAddress(clientDTO);
			Client client = new Client(clientDTO);
			return clientRepository.save(client);
		}
	}



	@Override
	public Client updateClient(ClientDTO clientDTO) {
		getClientById(clientDTO.getId());
		if(clientDTO.getName() == null || clientDTO.getNif() == null || clientDTO.getAddress() == null || clientDTO.getEmail() == null || clientDTO.getPassword() == null)
			throw new MethodArgumentNotValidException("Fields NAME, NIF, ADDRESS, EMAIL and PASSWORD are mandatory");
		else {
			validateNifEmailAndAddress(clientDTO);
			Client client = new Client(clientDTO);
			return clientRepository.save(client);
		}
	}

	@Override
	public void deleteClient(Integer id) {
		Client client = getClientById(id);
		if(client.getRequests().size() > 0)
			throw new DataIntegrityViolationException("Client id " + client.getId() + " has requests and cannot be deleted");
		else
			clientRepository.deleteById(client.getId());
	}
	
	//Auxiliary Methods
	private void validateNifEmailAndAddress(ClientDTO clientDTO) {
		Optional<Person> obj = personRepository.findByNif(clientDTO.getNif());
		if(obj.isPresent() && obj.get().getId() != clientDTO.getId())
			throw new DataIntegrityViolationException("NIF " + clientDTO.getNif() + " already in system!");
		
		obj = personRepository.findByEmail(clientDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != clientDTO.getId())
			throw new DataIntegrityViolationException("Email " + clientDTO.getEmail() + " already in system!");
		
		obj = personRepository.findByAddress(clientDTO.getAddress());
		if(obj.isPresent() && obj.get().getId() != clientDTO.getId())
			throw new DataIntegrityViolationException("Address " + clientDTO.getAddress() + " already in system!");
	}

}
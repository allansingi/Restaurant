package com.allanborges.restaurantAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

/*
 * Methods Implementation for controller layer end-points usage
 */
@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	
	@Override
	public List<Client> getAllClient() {
		return clientRepository.findAll();
	}

	@Override
	public Client getClientById(Integer id) {
		Optional<Client> client = clientRepository.findById(id);
		return client.orElseThrow(() -> new ObjectNotFoundException("Client with id " + id + " not found"));
	}

	@Override
	public Client addClient(ClientDTO clientDTO) {
		clientDTO.setId(null);
		clientDTO.setPassword(encoder.encode(clientDTO.getPassword()));
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
		//id Field
		Client currentClient = getClientById(clientDTO.getId());
		
		//name Field
		if(clientDTO.getName() == null)
			clientDTO.setName(currentClient.getName());
		else
			currentClient.setName(clientDTO.getName());
		
		//nif Field
		if(clientDTO.getNif() == null)
			clientDTO.setNif(currentClient.getNif());
		else
			currentClient.setNif(clientDTO.getNif());
		
		//address Field
		if(clientDTO.getAddress() == null)
			clientDTO.setAddress(currentClient.getAddress());
		else
			currentClient.setAddress(clientDTO.getAddress());
		
		//email Field
		if(clientDTO.getEmail() == null)
			clientDTO.setEmail(currentClient.getEmail());
		else
			currentClient.setEmail(clientDTO.getEmail());
		
		//password Field
		if(clientDTO.getPassword() == null)
			clientDTO.setPassword(currentClient.getPassword());
		else
			currentClient.setPassword(clientDTO.getPassword());
		
		validateNifEmailAndAddress(currentClient);
		currentClient = new Client(clientDTO);
		return clientRepository.save(currentClient);
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
	private void validateNifEmailAndAddress(Client client) {
		Optional<Person> obj = personRepository.findByNif(client.getNif());
		if(obj.isPresent() && obj.get().getId() != client.getId())
			throw new DataIntegrityViolationException("NIF " + client.getNif() + " already in system!");
		
		obj = personRepository.findByEmail(client.getEmail());
		if(obj.isPresent() && obj.get().getId() != client.getId())
			throw new DataIntegrityViolationException("Email " + client.getEmail() + " already in system!");
		
		obj = personRepository.findByAddress(client.getAddress());
		if(obj.isPresent() && obj.get().getId() != client.getId())
			throw new DataIntegrityViolationException("Address " + client.getAddress() + " already in system!");
	}
	
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
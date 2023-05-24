package com.allanborges.restaurantAPI.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allanborges.restaurantAPI.domain.Client;
import com.allanborges.restaurantAPI.domain.dtos.ClientDTO;
import com.allanborges.restaurantAPI.domain.response.ResponseClient;
import com.allanborges.restaurantAPI.services.interfaces.ClientService;
import com.allanborges.restaurantAPI.util.DateGenerator;

@RestController
@RequestMapping("/api/v1")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	DateGenerator dateGenerator;

	@GetMapping(value = "/clientList")
	public ResponseEntity<ResponseClient> clientList() {
		ResponseClient responseClient = new ResponseClient();
		responseClient.setSentOn(dateGenerator.generateCurrentDate());
		responseClient.setTransactionId(UUID.randomUUID().toString());
		try {
            List<Client> list = clientService.getAllClient();
            List<ClientDTO> listDTO = list.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
            
            responseClient.setStatus("OK");
            responseClient.setStatusCode("200");
            responseClient.setMsg("Method getAllClient Success");
            responseClient.setResValues(listDTO);
        } catch (Exception e) {
        	responseClient.setStatus("NOK");
        	responseClient.setStatusCode("500");
        	responseClient.setMsg("Method getAllClient Error: " + e.getMessage());
        	responseClient.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseClient);
	}
	
	@PostMapping(value = "/clientById")
	public ResponseEntity<ResponseClient> clientById(@RequestBody Client client) {
		ResponseClient responseClient = new ResponseClient();
		responseClient.setSentOn(dateGenerator.generateCurrentDate());
		responseClient.setTransactionId(UUID.randomUUID().toString());
		try {
            Client currentClient = clientService.getClientById(client.getId());
            List<Client> currentClientList = new ArrayList<>();
            currentClientList.add(currentClient);
            List<ClientDTO> listDTO = currentClientList.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
            
            responseClient.setStatus("OK");
            responseClient.setStatusCode("200");
            responseClient.setMsg("Method getClientById Success");
            responseClient.setResValues(listDTO);
        } catch (Exception e) {
        	responseClient.setStatus("NOK");
        	responseClient.setStatusCode("500");
        	responseClient.setMsg("Method getClientById Error: " + e.getMessage());
        	responseClient.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseClient);
	}
	
	@PostMapping(value = "/addClient")
	public ResponseEntity<ResponseClient> addClient(@RequestBody ClientDTO clientDTO) {
		ResponseClient responseClient = new ResponseClient();
		responseClient.setSentOn(dateGenerator.generateCurrentDate());
		responseClient.setTransactionId(UUID.randomUUID().toString());
		try {
            Client currentClient = clientService.addClient(clientDTO);
            List<Client> currentClientList = new ArrayList<>();
            currentClientList.add(currentClient);
            List<ClientDTO> listDTO = currentClientList.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
            
            responseClient.setStatus("OK");
            responseClient.setStatusCode("200");
            responseClient.setMsg("Method addClient Success");
            responseClient.setResValues(listDTO);
        } catch (Exception e) {
        	responseClient.setStatus("NOK");
        	responseClient.setStatusCode("500");
        	responseClient.setMsg("Method addClient Error: " + e.getMessage());
        	responseClient.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseClient);
	}
	
	@PostMapping(value = "/updateClient")
	public ResponseEntity<ResponseClient> updateClient(@RequestBody ClientDTO clientDTO) {
		ResponseClient responseClient = new ResponseClient();
		responseClient.setSentOn(dateGenerator.generateCurrentDate());
		responseClient.setTransactionId(UUID.randomUUID().toString());
		try {
            Client currentClient = clientService.updateClient(clientDTO);
            List<Client> currentClientList = new ArrayList<>();
            currentClientList.add(currentClient);
            List<ClientDTO> listDTO = currentClientList.stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
            
            responseClient.setStatus("OK");
            responseClient.setStatusCode("200");
            responseClient.setMsg("Method updateClient Success");
            responseClient.setResValues(listDTO);
        } catch (Exception e) {
        	responseClient.setStatus("NOK");
        	responseClient.setStatusCode("500");
        	responseClient.setMsg("Method updateClient Error: " + e.getMessage());
        	responseClient.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseClient);
	}
	
	@DeleteMapping(value = "/deleteClientById")
	public ResponseEntity<ResponseClient> deleteClient(@RequestBody ClientDTO clientDTO) {
		ResponseClient responseClient = new ResponseClient();
		responseClient.setSentOn(dateGenerator.generateCurrentDate());
		responseClient.setTransactionId(UUID.randomUUID().toString());
		try {
            clientService.deleteClient(clientDTO.getId());
            
            responseClient.setStatus("OK");
            responseClient.setStatusCode("200");
            responseClient.setMsg("Method deleteClient Success");
            responseClient.setResValues(new ArrayList<>());
        } catch (Exception e) {
        	responseClient.setStatus("NOK");
        	responseClient.setStatusCode("500");
        	responseClient.setMsg("Method deleteClient Error: " + e.getMessage());
        	responseClient.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseClient);
	}
	
}
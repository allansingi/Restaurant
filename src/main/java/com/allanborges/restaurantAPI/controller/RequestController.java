package com.allanborges.restaurantAPI.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allanborges.restaurantAPI.domain.Request;
import com.allanborges.restaurantAPI.domain.dtos.RequestDTO;
import com.allanborges.restaurantAPI.domain.response.ResponseRequest;
import com.allanborges.restaurantAPI.services.interfaces.RequestService;
import com.allanborges.restaurantAPI.util.DateGenerator;

/*
 *  Rest Controller Class for Request Module end-points  
 */
@RestController
@RequestMapping("/api/v1")
public class RequestController {

	@Autowired
	private RequestService requestService;
	@Autowired
	DateGenerator dateGenerator;
	
	/*
	 * Returns a list of created requests in data base
	 * @return requestDTO list of requests
	 */
	@GetMapping(value = "/requestList")
	public ResponseEntity<ResponseRequest> requestList() {
		ResponseRequest responseRequest = new ResponseRequest();
		responseRequest.setSentOn(dateGenerator.generateCurrentDate());
		responseRequest.setTransactionId(UUID.randomUUID().toString());
		try {
            List<Request> list = requestService.getAllRequests();
            List<RequestDTO> listDTO = list.stream().map(x -> new RequestDTO(x)).collect(Collectors.toList());
            
            responseRequest.setStatus("OK");
            responseRequest.setStatusCode("200");
            responseRequest.setMsg("Method getAllRequests Success");
            responseRequest.setResValues(listDTO);
        } catch (Exception e) {
        	responseRequest.setStatus("NOK");
        	responseRequest.setStatusCode("500");
        	responseRequest.setMsg("Method getAllRequests Error: " + e.getMessage());
        	responseRequest.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseRequest);
	}
	
	/*
	 * Returns a list of created requests in data base by request_status = 2 "READY"
	 * @return requestDTO list of requests
	 */
	@GetMapping(value = "/requestReadyList")
	public ResponseEntity<ResponseRequest> requestReadyList() {
		ResponseRequest responseRequest = new ResponseRequest();
		responseRequest.setSentOn(dateGenerator.generateCurrentDate());
		responseRequest.setTransactionId(UUID.randomUUID().toString());
		try {
            List<Request> list = requestService.getReadyRequests();
            List<RequestDTO> listDTO = list.stream().map(x -> new RequestDTO(x)).collect(Collectors.toList());
            
            responseRequest.setStatus("OK");
            responseRequest.setStatusCode("200");
            responseRequest.setMsg("Method getReadyRequests Success");
            responseRequest.setResValues(listDTO);
        } catch (Exception e) {
        	responseRequest.setStatus("NOK");
        	responseRequest.setStatusCode("500");
        	responseRequest.setMsg("Method getReadyRequests Error: " + e.getMessage());
        	responseRequest.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseRequest);
	}
	
	/*
	 * Returns a requestDTO by its id
	 * Integer id in a json object. Example:
	 * @param json {"id": 1}
	 * @return a list with the requested requestDTO by its id
	 */
	@PostMapping(value = "/requestById")
	public ResponseEntity<ResponseRequest> requestById(@RequestBody Request request) {
		ResponseRequest responseRequest = new ResponseRequest();
		responseRequest.setSentOn(dateGenerator.generateCurrentDate());
		responseRequest.setTransactionId(UUID.randomUUID().toString());
		try {
            Request currentRequest = requestService.getRequestById(request.getId());
            List<Request> currentRequestList = new ArrayList<>();
            currentRequestList.add(currentRequest);
            List<RequestDTO> listDTO = currentRequestList.stream().map(x -> new RequestDTO(x)).collect(Collectors.toList());
            
            responseRequest.setStatus("OK");
            responseRequest.setStatusCode("200");
            responseRequest.setMsg("Method getRequestById Success");
            responseRequest.setResValues(listDTO);
        } catch (Exception e) {
        	responseRequest.setStatus("NOK");
        	responseRequest.setStatusCode("500");
        	responseRequest.setMsg("Method getRequestById Error: " + e.getMessage());
        	responseRequest.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseRequest);
	}
	
	/*
	 * Create request object with data base writing
	 * RequestDTO with all mandatory fields in a json object. Example:
	 * @param json {"clientId": 4, "requestedMenuId": 3, "requestedQuantity": 5}
	 * @return a list with the created requestDTO object
	 */
	@PostMapping(value = "/createRequest")
	public ResponseEntity<ResponseRequest> createRequest(@RequestBody RequestDTO requestDTO) {
		ResponseRequest responseRequest = new ResponseRequest();
		responseRequest.setSentOn(dateGenerator.generateCurrentDate());
		responseRequest.setTransactionId(UUID.randomUUID().toString());
		try {
            Request currentRequest = requestService.createRequest(requestDTO);
            List<Request> currentRequestList = new ArrayList<>();
            currentRequestList.add(currentRequest);
            List<RequestDTO> listDTO = currentRequestList.stream().map(x -> new RequestDTO(x)).collect(Collectors.toList());
            
            responseRequest.setStatus("OK");	
            responseRequest.setStatusCode("200");
            responseRequest.setMsg("Method createRequest Success");
            responseRequest.setResValues(listDTO);
        } catch (Exception e) {
        	responseRequest.setStatus("NOK");
        	responseRequest.setStatusCode("500");
        	responseRequest.setMsg("Method createRequest Error: " + e.getMessage());
        	responseRequest.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseRequest);
	}
	
	/*
	 * Request object update with data base updating
	 * RequestDTO with, at least, id, requestedMenuId and requestedQuantity fields in order to update requested fields in a json object. Example:
	 * @param json {"id": 1, "requestedMenuId": "4", "requestedQuantity": 5}
	 * @return a list with the updated requestDTO object
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(value = "/updateRequest")
	public ResponseEntity<ResponseRequest> updateRequest(@RequestBody RequestDTO requestDTO) {
		ResponseRequest responseRequest = new ResponseRequest();
		responseRequest.setSentOn(dateGenerator.generateCurrentDate());
		responseRequest.setTransactionId(UUID.randomUUID().toString());
		try {
            Request currentRequest = requestService.updateRequest(requestDTO);
            List<Request> currentRequestList = new ArrayList<>();
            currentRequestList.add(currentRequest);
            List<RequestDTO> listDTO = currentRequestList.stream().map(x -> new RequestDTO(x)).collect(Collectors.toList());
            
            responseRequest.setStatus("OK");	
            responseRequest.setStatusCode("200");
            responseRequest.setMsg("Method updateRequest Success");
            responseRequest.setResValues(listDTO);
        } catch (Exception e) {
        	responseRequest.setStatus("NOK");
        	responseRequest.setStatusCode("500");
        	responseRequest.setMsg("Method updateRequest Error: " + e.getMessage());
        	responseRequest.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseRequest);
	}
	
}
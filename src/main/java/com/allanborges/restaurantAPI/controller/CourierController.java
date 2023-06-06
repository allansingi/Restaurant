package com.allanborges.restaurantAPI.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allanborges.restaurantAPI.domain.Courier;
import com.allanborges.restaurantAPI.domain.dtos.CourierDTO;
import com.allanborges.restaurantAPI.domain.response.ResponseCourier;
import com.allanborges.restaurantAPI.services.interfaces.CourierService;
import com.allanborges.restaurantAPI.util.DateGenerator;

/*
 *  Rest Controller Class for Courier Module end-points  
 */
@RestController
@RequestMapping("/api/v1")
public class CourierController {

	@Autowired
	private CourierService courierService;
	@Autowired
	DateGenerator dateGenerator;
	
	/*
	 * Returns a list of created couriers in data base
	 * @return courierDTO list of couriers
	 */
	@GetMapping(value = "/courierList")
	public ResponseEntity<ResponseCourier> courierList() {
		ResponseCourier responseCourier = new ResponseCourier();
		responseCourier.setSentOn(dateGenerator.generateCurrentDate());
		responseCourier.setTransactionId(UUID.randomUUID().toString());
		try {
            List<Courier> list = courierService.getAllCourier();
            List<CourierDTO> listDTO = list.stream().map(x -> new CourierDTO(x)).collect(Collectors.toList());
            
            responseCourier.setStatus("OK");
            responseCourier.setStatusCode("200");
            responseCourier.setMsg("Method getAllCourier Success");
            responseCourier.setResValues(listDTO);
        } catch (Exception e) {
        	responseCourier.setStatus("NOK");
        	responseCourier.setStatusCode("500");
        	responseCourier.setMsg("Method getAllCourier Error: " + e.getMessage());
        	responseCourier.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseCourier);
	}
	
	/*
	 * Returns a courierDTO by its id
	 * Integer id in a json object. Example:
	 * @param json {"id": 1}
	 * @return a list with the requested courierDTO by its id
	 */
	@PostMapping(value = "/courierById")
	public ResponseEntity<ResponseCourier> courierById(@RequestBody Courier courier) {
		ResponseCourier responseCourier = new ResponseCourier();
		responseCourier.setSentOn(dateGenerator.generateCurrentDate());
		responseCourier.setTransactionId(UUID.randomUUID().toString());
		try {
			Courier currentCourier = courierService.getCourierById(courier.getId());
            List<Courier> currentCourierList = new ArrayList<>();
            currentCourierList.add(currentCourier);
            List<CourierDTO> listDTO = currentCourierList.stream().map(x -> new CourierDTO(x)).collect(Collectors.toList());
            
            responseCourier.setStatus("OK");
            responseCourier.setStatusCode("200");
            responseCourier.setMsg("Method getCourierById Success");
            responseCourier.setResValues(listDTO);
        } catch (Exception e) {
        	responseCourier.setStatus("NOK");
        	responseCourier.setStatusCode("500");
        	responseCourier.setMsg("Method getCourierById Error: " + e.getMessage());
        	responseCourier.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseCourier);
	}
	
	/*
	 * Create courier object with data base writing
	 * CourierDTO with all mandatory fields in a json object. Example:
	 * @param json {"name": "foo", "nif": "123456789", "address": "foo st. 1", "email": "foo@mail.com", "password": "123"}
	 * @return a list with the created courierDTO object
	 */
	@PostMapping(value = "/addCourier")
	public ResponseEntity<ResponseCourier> addCourier(@RequestBody CourierDTO courierDTO) {
		ResponseCourier responseCourier = new ResponseCourier();
		responseCourier.setSentOn(dateGenerator.generateCurrentDate());
		responseCourier.setTransactionId(UUID.randomUUID().toString());
		try {
			Courier currentCourier = courierService.addCourier(courierDTO);
            List<Courier> currentCourierList = new ArrayList<>();
            currentCourierList.add(currentCourier);
            List<CourierDTO> listDTO = currentCourierList.stream().map(x -> new CourierDTO(x)).collect(Collectors.toList());
            
            responseCourier.setStatus("OK");
            responseCourier.setStatusCode("200");
            responseCourier.setMsg("Method addCourier Success");
            responseCourier.setResValues(listDTO);
        } catch (Exception e) {
        	responseCourier.setStatus("NOK");
        	responseCourier.setStatusCode("500");
        	responseCourier.setMsg("Method addCourier Error: " + e.getMessage());
        	responseCourier.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseCourier);
	}
	
	/*
	 * Courier object update with data base updating
	 * CourierDTO with, at least, id field in order to update requested field in a json object. Example:
	 * @param json {"id": 1, "name": "foo2"}
	 * @return a list with the updated courierDTO object
	 */
	@PostMapping(value = "/updateCourier")
	public ResponseEntity<ResponseCourier> updateCourier(@RequestBody CourierDTO courierDTO) {
		ResponseCourier responseCourier = new ResponseCourier();
		responseCourier.setSentOn(dateGenerator.generateCurrentDate());
		responseCourier.setTransactionId(UUID.randomUUID().toString());
		try {
			Courier currentCourier = courierService.updateCourier(courierDTO);
            List<Courier> currentCourierList = new ArrayList<>();
            currentCourierList.add(currentCourier);
            List<CourierDTO> listDTO = currentCourierList.stream().map(x -> new CourierDTO(x)).collect(Collectors.toList());
            
            responseCourier.setStatus("OK");
            responseCourier.setStatusCode("200");
            responseCourier.setMsg("Method updateCourier Success");
            responseCourier.setResValues(listDTO);
        } catch (Exception e) {
        	responseCourier.setStatus("NOK");
        	responseCourier.setStatusCode("500");
        	responseCourier.setMsg("Method updateCourier Error: " + e.getMessage());
        	responseCourier.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseCourier);
	}
	
	/*
	 * Delete a courier by its id. Only persons with ADMIN role
	 * Integer id in a json object. Example:
	 * @param json {"id": 1}
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/deleteCourierById")
	public ResponseEntity<ResponseCourier> deleteCourierById(@RequestBody CourierDTO courierDTO) {
		ResponseCourier responseCourier = new ResponseCourier();
		responseCourier.setSentOn(dateGenerator.generateCurrentDate());
		responseCourier.setTransactionId(UUID.randomUUID().toString());
		try {
			courierService.deleteCourier(courierDTO.getId());
            
            responseCourier.setStatus("OK");
            responseCourier.setStatusCode("200");
            responseCourier.setMsg("Method deleteCourier Success");
            responseCourier.setResValues(new ArrayList<>());
        } catch (Exception e) {
        	responseCourier.setStatus("NOK");
        	responseCourier.setStatusCode("500");
        	responseCourier.setMsg("Method deleteCourier Error: " + e.getMessage());
        	responseCourier.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseCourier);
	}
	
}
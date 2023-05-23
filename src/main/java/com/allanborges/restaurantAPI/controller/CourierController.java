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

import com.allanborges.restaurantAPI.domain.Courier;
import com.allanborges.restaurantAPI.domain.dtos.CourierDTO;
import com.allanborges.restaurantAPI.domain.response.ResponseCourier;
import com.allanborges.restaurantAPI.services.interfaces.CourierService;
import com.allanborges.restaurantAPI.util.DateGenerator;

@RestController
@RequestMapping("/api/v1")
public class CourierController {

	@Autowired
	private CourierService courierService;
	
	@Autowired
	DateGenerator dateGenerator;
	
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
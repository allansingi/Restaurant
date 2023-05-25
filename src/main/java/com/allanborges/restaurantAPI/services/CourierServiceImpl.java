package com.allanborges.restaurantAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allanborges.restaurantAPI.domain.Courier;
import com.allanborges.restaurantAPI.domain.Person;
import com.allanborges.restaurantAPI.domain.dtos.CourierDTO;
import com.allanborges.restaurantAPI.repositories.CourierRepository;
import com.allanborges.restaurantAPI.repositories.PersonRepository;
import com.allanborges.restaurantAPI.services.exceptions.DataIntegrityViolationException;
import com.allanborges.restaurantAPI.services.exceptions.MethodArgumentNotValidException;
import com.allanborges.restaurantAPI.services.exceptions.ObjectNotFoundException;
import com.allanborges.restaurantAPI.services.interfaces.CourierService;

@Service
public class CourierServiceImpl implements CourierService {
	
	@Autowired
	private CourierRepository courierRepository;
	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Courier> getAllCourier() {
		return courierRepository.findAll();
	}

	@Override
	public Courier getCourierById(Integer id) {
		Optional<Courier> courier = courierRepository.findById(id);
		return courier.orElseThrow(() -> new ObjectNotFoundException("Courier id " + id + " not found"));
	}

	@Override
	public Courier addCourier(CourierDTO courierDTO) {
		courierDTO.setId(null);
		if(courierDTO.getName() == null || courierDTO.getNif() == null || courierDTO.getAddress() == null || courierDTO.getEmail() == null || courierDTO.getPassword() == null)
			throw new MethodArgumentNotValidException("Fields NAME, NIF, ADDRESS, EMAIL and PASSWORD are mandatory");
		else {
			validateNifEmailAndAddress(courierDTO);
			Courier courier = new Courier(courierDTO);
			return courierRepository.save(courier);
		}
	}

	@Override
	public Courier updateCourier(CourierDTO courierDTO) {
		getCourierById(courierDTO.getId());
		if(courierDTO.getName() == null || courierDTO.getNif() == null || courierDTO.getAddress() == null || courierDTO.getEmail() == null || courierDTO.getPassword() == null)
			throw new MethodArgumentNotValidException("Fields NAME, NIF, ADDRESS, EMAIL and PASSWORD are mandatory");
		else {
			validateNifEmailAndAddress(courierDTO);
			Courier courier = new Courier(courierDTO);
			return courierRepository.save(courier);
		}
	}

	@Override
	public void deleteCourier(Integer id) {
		Courier courier = getCourierById(id);
		if(courier.getRequests().size() > 0)
			throw new DataIntegrityViolationException("Client id " + courier.getId() + " has requests and cannot be deleted");
		else
			courierRepository.deleteById(courier.getId());
	}
	
	//Auxiliary Methods
	private void validateNifEmailAndAddress(CourierDTO courierDTO) {
		Optional<Person> obj = personRepository.findByNif(courierDTO.getNif());
		if(obj.isPresent() && obj.get().getId() != courierDTO.getId())
			throw new DataIntegrityViolationException("NIF " + courierDTO.getNif() + " already in system!");
		
		obj = personRepository.findByEmail(courierDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != courierDTO.getId())
			throw new DataIntegrityViolationException("Email " + courierDTO.getEmail() + " already in system!");
		
		obj = personRepository.findByAddress(courierDTO.getAddress());
		if(obj.isPresent() && obj.get().getId() != courierDTO.getId())
			throw new DataIntegrityViolationException("Address " + courierDTO.getAddress() + " already in system!");
	}

}
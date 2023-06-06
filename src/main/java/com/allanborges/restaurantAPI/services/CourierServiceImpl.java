package com.allanborges.restaurantAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

/*
 * Methods Implementation for controller layer end-points usage
 */
@Service
public class CourierServiceImpl implements CourierService {
	
	@Autowired
	private CourierRepository courierRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
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
		courierDTO.setPassword(encoder.encode(courierDTO.getPassword()));
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
		//id Field
		Courier currentCourier = getCourierById(courierDTO.getId());
		
		//name Field
		if(courierDTO.getName() == null)
			courierDTO.setName(currentCourier.getName());
		else
			currentCourier.setName(courierDTO.getName());
		
		//nif Field
		if(courierDTO.getNif() == null)
			courierDTO.setNif(currentCourier.getNif());
		else
			currentCourier.setNif(courierDTO.getNif());
		
		//address Field
		if(courierDTO.getAddress() == null)
			courierDTO.setAddress(currentCourier.getAddress());
		else
			currentCourier.setAddress(courierDTO.getAddress());
		
		//email Field
		if(courierDTO.getEmail() == null)
			courierDTO.setEmail(currentCourier.getEmail());
		else
			currentCourier.setEmail(courierDTO.getEmail());
		
		//password Field
		if(courierDTO.getPassword() == null)
			courierDTO.setPassword(currentCourier.getPassword());
		else
			currentCourier.setPassword(courierDTO.getPassword());
		
		validateNifEmailAndAddress(currentCourier);
		currentCourier = new Courier(courierDTO);
		return courierRepository.save(currentCourier);
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
	
	private void validateNifEmailAndAddress(Courier courier) {
		Optional<Person> obj = personRepository.findByNif(courier.getNif());
		if(obj.isPresent() && obj.get().getId() != courier.getId())
			throw new DataIntegrityViolationException("NIF " + courier.getNif() + " already in system!");
		
		obj = personRepository.findByEmail(courier.getEmail());
		if(obj.isPresent() && obj.get().getId() != courier.getId())
			throw new DataIntegrityViolationException("Email " + courier.getEmail() + " already in system!");
		
		obj = personRepository.findByAddress(courier.getAddress());
		if(obj.isPresent() && obj.get().getId() != courier.getId())
			throw new DataIntegrityViolationException("Address " + courier.getAddress() + " already in system!");
	}

}
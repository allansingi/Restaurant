package com.allanborges.restaurantAPI.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanborges.restaurantAPI.domain.Person;

/*
 * JPA Repository for Person Abstract Entity Objects
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	//Database fetch of person objects by nif to serve validation methods
	Optional<Person> findByNif(String nif);
	
	//Database fetch of person objects by email to serve validation methods
	Optional<Person> findByEmail(String email);
	
	//Database fetch of person objects by address to serve validation methods
	Optional<Person> findByAddress(String address);

}
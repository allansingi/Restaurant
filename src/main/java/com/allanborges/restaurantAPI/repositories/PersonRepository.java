package com.allanborges.restaurantAPI.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanborges.restaurantAPI.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	
	Optional<Person> findByNif(String nif);
	Optional<Person> findByEmail(String email);
	Optional<Person> findByAddress(String address);

}
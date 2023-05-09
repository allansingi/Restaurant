package com.allanborges.restaurantAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanborges.restaurantAPI.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
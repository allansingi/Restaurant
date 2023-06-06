package com.allanborges.restaurantAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanborges.restaurantAPI.domain.Client;

/*
 * JPA Repository for Client Entity Objects
 */
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
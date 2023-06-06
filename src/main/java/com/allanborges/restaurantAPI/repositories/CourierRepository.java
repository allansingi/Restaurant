package com.allanborges.restaurantAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanborges.restaurantAPI.domain.Courier;

/*
 * JPA Repository for Courier Entity Objects
 */
public interface CourierRepository extends JpaRepository<Courier, Integer> {

}
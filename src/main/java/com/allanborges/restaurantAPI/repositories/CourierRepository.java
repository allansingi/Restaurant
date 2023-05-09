package com.allanborges.restaurantAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanborges.restaurantAPI.domain.Courier;

public interface CourierRepository extends JpaRepository<Courier, Integer> {

}
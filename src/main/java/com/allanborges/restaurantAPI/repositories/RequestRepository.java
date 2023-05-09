package com.allanborges.restaurantAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanborges.restaurantAPI.domain.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {

}
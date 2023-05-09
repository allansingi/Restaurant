package com.allanborges.restaurantAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allanborges.restaurantAPI.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

}
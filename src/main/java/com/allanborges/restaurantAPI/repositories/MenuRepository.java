package com.allanborges.restaurantAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allanborges.restaurantAPI.domain.Menu;

/*
 * JPA Repository for Menu Entity Objects
 */
public interface MenuRepository extends JpaRepository<Menu, Integer> {
	
	//Database fetch of active menu objects by expire_date >= CURDATE() and active = true
	@Query(value = "SELECT * FROM menu WHERE expire_date >= CURDATE() AND active = true", nativeQuery = true)
    List<Menu> findActiveMenu();

}
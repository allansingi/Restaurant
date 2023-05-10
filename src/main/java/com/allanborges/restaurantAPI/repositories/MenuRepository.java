package com.allanborges.restaurantAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allanborges.restaurantAPI.domain.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
	
	@Query(value = "SELECT * FROM menu WHERE expire_date >= CURDATE() AND active = true", nativeQuery = true)
    List<Menu> findActiveMenu();

}
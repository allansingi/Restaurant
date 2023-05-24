package com.allanborges.restaurantAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allanborges.restaurantAPI.domain.Request;

public interface RequestRepository extends JpaRepository<Request, Integer> {

	List<Request> findByRequestedMenuId(Integer id);
	
	@Query(value = "SELECT * FROM REQUEST WHERE request_status = 2", nativeQuery = true)
    List<Request> findReadyRequests();
	/*
	@Query(value = "SELECT * FROM request WHERE courier_id IS NULL", nativeQuery = true)
    List<Request> findNullCourierRequests();
	*/
}
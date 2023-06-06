package com.allanborges.restaurantAPI.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allanborges.restaurantAPI.domain.Request;

/*
 * JPA Repository for Request Entity Objects
 */
public interface RequestRepository extends JpaRepository<Request, Integer> {

	//Database fetch of request object by id
	List<Request> findByRequestedMenuId(Integer id);
	
	//Database fetch of requests with request_status = 2 READY
	@Query(value = "SELECT * FROM REQUEST WHERE request_status = 2", nativeQuery = true)
    List<Request> findReadyRequests();
	
}
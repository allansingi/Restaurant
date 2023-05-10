package com.allanborges.restaurantAPI.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allanborges.restaurantAPI.domain.Menu;

@Service
public interface MenuService {
	
	List<Menu> getAllMenu();
	List<Menu> getActiveMenu();
	Menu addMenu(Menu menu);

}
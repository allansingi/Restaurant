package com.allanborges.restaurantAPI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allanborges.restaurantAPI.domain.Menu;
import com.allanborges.restaurantAPI.repositories.MenuRepository;
import com.allanborges.restaurantAPI.services.interfaces.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Override
	public List<Menu> getAllMenu() {
		return menuRepository.findAll();
	}

	@Override
	public List<Menu> getActiveMenu() {
		return menuRepository.findActiveMenu();
	}

	@Override
	public Menu addMenu(Menu menu) {
		
		return null;
	}

}
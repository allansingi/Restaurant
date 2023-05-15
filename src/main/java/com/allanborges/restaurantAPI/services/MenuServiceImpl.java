package com.allanborges.restaurantAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allanborges.restaurantAPI.domain.Menu;
import com.allanborges.restaurantAPI.domain.dtos.MenuDTO;
import com.allanborges.restaurantAPI.repositories.MenuRepository;
import com.allanborges.restaurantAPI.services.exceptions.ObjectNotFoundException;
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
	public Menu getMenuById(Integer id) {
		try {
			Optional<Menu> menu = menuRepository.findById(id);
			return menu.get();
		} catch (Exception e) {
			throw new ObjectNotFoundException("Object with id " + id + " not found");
		}
	}
	
	@Override
	public Menu addMenu(MenuDTO menuDTO) {
		menuDTO.setId(null);
		Menu menu = new Menu(menuDTO);
		return menuRepository.save(menu);
	}

}
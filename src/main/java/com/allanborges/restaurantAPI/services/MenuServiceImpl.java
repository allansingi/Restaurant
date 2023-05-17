package com.allanborges.restaurantAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allanborges.restaurantAPI.domain.Menu;
import com.allanborges.restaurantAPI.domain.Request;
import com.allanborges.restaurantAPI.domain.dtos.MenuDTO;
import com.allanborges.restaurantAPI.repositories.MenuRepository;
import com.allanborges.restaurantAPI.repositories.RequestRepository;
import com.allanborges.restaurantAPI.services.exceptions.DataIntegrityViolationException;
import com.allanborges.restaurantAPI.services.exceptions.MethodArgumentNotValidException;
import com.allanborges.restaurantAPI.services.exceptions.ObjectNotFoundException;
import com.allanborges.restaurantAPI.services.interfaces.MenuService;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
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
		if(menuDTO.getName() == null || menuDTO.getDescription() == null || menuDTO.getPrice() == null || menuDTO.getQuantity() == null || menuDTO.getActive() == null || menuDTO.getExpireDate() == null)
			throw new MethodArgumentNotValidException("Fields NAME, DESCRIPTION, PRICE, QUANTITY, ACTIVE and EXPIREDATE are mandatory");
		else {
			Menu menu = new Menu(menuDTO);
			return menuRepository.save(menu);
		}
	}

	@Override
	public Menu updateMenu(MenuDTO menuDTO) {
		if(menuDTO.getName() == null || menuDTO.getDescription() == null || menuDTO.getPrice() == null || menuDTO.getQuantity() == null || menuDTO.getActive() == null || menuDTO.getExpireDate() == null)
			throw new MethodArgumentNotValidException("Fields NAME, DESCRIPTION, PRICE, QUANTITY, ACTIVE and EXPIREDATE are mandatory");
		else {
			Menu currentMenu = getMenuById(menuDTO.getId());
			currentMenu = new Menu(menuDTO);
			return menuRepository.save(currentMenu);
		}
	}

	@Override
	public void deleteMenu(Integer id) {
		getMenuById(id);
		List<Request> list = new ArrayList<>();
		list = requestRepository.findByRequestedMenuId(id);
		
		if(!list.isEmpty()) {
			throw new DataIntegrityViolationException("The Menu has a request and can not be deleted!");
		}
		
		menuRepository.deleteById(id);
	}

}
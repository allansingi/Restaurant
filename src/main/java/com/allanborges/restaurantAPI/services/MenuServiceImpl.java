package com.allanborges.restaurantAPI.services;

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
		Optional<Menu> menu = menuRepository.findById(id);
		return menu.orElseThrow(() -> new ObjectNotFoundException("Menu with id " + id + " not found"));
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
		//id Field
		Menu currentMenu = getMenuById(menuDTO.getId());
		
		//name Field
		if(menuDTO.getName() == null)
			menuDTO.setName(currentMenu.getName());
		else
			currentMenu.setName(menuDTO.getName());
		
		//menuDescription Field
		if(menuDTO.getDescription() == null)
			menuDTO.setDescription(currentMenu.getDescription());
		else
			currentMenu.setDescription(menuDTO.getDescription());
		
		//price Field
		if(menuDTO.getPrice() == null)
			menuDTO.setPrice(currentMenu.getPrice());
		else
			currentMenu.setPrice(menuDTO.getPrice());
		
		//quantity Field
		if(menuDTO.getQuantity() == null)
			menuDTO.setQuantity(currentMenu.getQuantity());
		else
			currentMenu.setQuantity(menuDTO.getQuantity());
		
		//active Field
		if(menuDTO.getActive() == null)
			menuDTO.setActive(currentMenu.getActive());
		else
			currentMenu.setActive(menuDTO.getActive());
		
		//expireDate Field
		if(menuDTO.getExpireDate() == null)
			menuDTO.setExpireDate(currentMenu.getExpireDate());
		else
			currentMenu.setExpireDate(menuDTO.getExpireDate());
		
		//imageURL Field
		if(menuDTO.getImageUrl() == null)
			menuDTO.setImageUrl(currentMenu.getImageUrl());
		else
			currentMenu.setImageUrl(menuDTO.getImageUrl());
		
		return menuRepository.save(currentMenu);
	}

	@Override
	public void deleteMenu(Integer id) {
		Menu menu = getMenuById(id);
		List<Request> list = requestRepository.findByRequestedMenuId(id);
		if(!list.isEmpty()) {
			throw new DataIntegrityViolationException("Menu " + menu.getId() + " has a request and can not be deleted!");
		}
		
		menuRepository.deleteById(id);
	}

}
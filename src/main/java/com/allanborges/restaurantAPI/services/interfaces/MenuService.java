package com.allanborges.restaurantAPI.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.allanborges.restaurantAPI.domain.Menu;
import com.allanborges.restaurantAPI.domain.dtos.MenuDTO;

/*
 * Methods for controller layer end-points usage
 */
@Service
public interface MenuService {
	
	List<Menu> getAllMenu();
	List<Menu> getActiveMenu();
	Menu getMenuById(Integer id);
	Menu addMenu(MenuDTO menuDTO);
	Menu updateMenu(MenuDTO menuDTO);
	void deleteMenu(Integer id);

}
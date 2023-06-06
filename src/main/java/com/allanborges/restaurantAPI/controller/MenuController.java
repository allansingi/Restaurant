package com.allanborges.restaurantAPI.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allanborges.restaurantAPI.domain.Menu;
import com.allanborges.restaurantAPI.domain.dtos.MenuDTO;
import com.allanborges.restaurantAPI.domain.response.ResponseMenu;
import com.allanborges.restaurantAPI.services.interfaces.MenuService;
import com.allanborges.restaurantAPI.util.DateGenerator;

/*
 *  Rest Controller Class for Menu Module end-points  
 */
@RestController
@RequestMapping("/api/v1")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	@Autowired
	DateGenerator dateGenerator;
	
	/*
	 * Returns a list of created menus in data base
	 * @return menuDTO list of menus
	 */
	@GetMapping(value = "/menuList")
	public ResponseEntity<ResponseMenu> menuList() {
		ResponseMenu responseMenu = new ResponseMenu();
		responseMenu.setSentOn(dateGenerator.generateCurrentDate());
		responseMenu.setTransactionId(UUID.randomUUID().toString());
		try {
            List<Menu> list = menuService.getAllMenu();
            List<MenuDTO> listDTO = list.stream().map(x -> new MenuDTO(x)).collect(Collectors.toList());
            
            responseMenu.setStatus("OK");
            responseMenu.setStatusCode("200");
            responseMenu.setMsg("Method getAllMenu Success");
            responseMenu.setResValues(listDTO);
        } catch (Exception e) {
            responseMenu.setStatus("NOK");
            responseMenu.setStatusCode("500");
            responseMenu.setMsg("Method getAllMenu Error: " + e.getMessage());
            responseMenu.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseMenu);
	}

	/*
	 * Returns a list of created menus in data base by currentDate >= expirationDate and active flag = true
	 * @return menuDTO list of menus
	 */
	@GetMapping(value = "/activeMenuList")
	public ResponseEntity<ResponseMenu> activeMenuList() {
		ResponseMenu responseMenu = new ResponseMenu();
		responseMenu.setSentOn(dateGenerator.generateCurrentDate());
		responseMenu.setTransactionId(UUID.randomUUID().toString());
		try {
            List<Menu> list = menuService.getActiveMenu();
            List<MenuDTO> listDTO = list.stream().map(x -> new MenuDTO(x)).collect(Collectors.toList());
            
            responseMenu.setStatus("OK");
            responseMenu.setStatusCode("200");
            responseMenu.setMsg("Method getActiveMenu Success");
            responseMenu.setResValues(listDTO);
        } catch (Exception e) {
            responseMenu.setStatus("NOK");
            responseMenu.setStatusCode("500");
            responseMenu.setMsg("Method getActiveMenu Error: " + e.getMessage());
            responseMenu.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseMenu);
	}
	
	/*
	 * Returns a menuDTO by its id
	 * Integer id in a json object. Example:
	 * @param json {"id": 1}
	 * @return a list with the requested menuDTO by its id
	 */
	@PostMapping(value = "/menuById")
	public ResponseEntity<ResponseMenu> menuById(@RequestBody Menu menu) {
		ResponseMenu responseMenu = new ResponseMenu();
		responseMenu.setSentOn(dateGenerator.generateCurrentDate());
		responseMenu.setTransactionId(UUID.randomUUID().toString());
		try {
            Menu currentMenu = menuService.getMenuById(menu.getId());
            List<Menu> currentMenuList = new ArrayList<>();
            currentMenuList.add(currentMenu);
            List<MenuDTO> currentMenuDTOList = currentMenuList.stream().map(x -> new MenuDTO(x)).collect(Collectors.toList());

            responseMenu.setStatus("OK");
            responseMenu.setStatusCode("200");
            responseMenu.setMsg("Method getMenuById Success");
            responseMenu.setResValues(currentMenuDTOList);
        } catch (Exception e) {
            responseMenu.setStatus("NOK");
            responseMenu.setStatusCode("500");
            responseMenu.setMsg("Method getMenuById Error: " + e.getMessage());
            responseMenu.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseMenu);
	}
	
	/*
	 * Create menu object with data base writing
	 * MenuDTO with all mandatory fields in a json object. Example:
	 * @param json {"name": "food", "description": "sea", "price": "99.99", "quantity": "9", "active": "true", "expireDate": "2099-12-31 12:00:00"}
	 * @return a list with the created menuDTO object
	 */
	@PostMapping(value = "/addMenu")
	public ResponseEntity<ResponseMenu> addMenu(@RequestBody MenuDTO menuDTO) {
		ResponseMenu responseMenu = new ResponseMenu();
		responseMenu.setSentOn(dateGenerator.generateCurrentDate());
		responseMenu.setTransactionId(UUID.randomUUID().toString());
		try {
            Menu addedMenu = menuService.addMenu(menuDTO);
            List<Menu> addedMenuList = new ArrayList<>();
            addedMenuList.add(addedMenu);
            List<MenuDTO> addedMenuDTO = addedMenuList.stream().map(x -> new MenuDTO(x)).collect(Collectors.toList());

            responseMenu.setStatus("OK");
            responseMenu.setStatusCode("200");
            responseMenu.setMsg("Method addMenu Success");
            responseMenu.setResValues(addedMenuDTO);
            
        } catch (Exception e) {
            responseMenu.setStatus("NOK");
            responseMenu.setStatusCode("500");
            responseMenu.setMsg("Method addMenu Error: " + e.getMessage());
            responseMenu.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseMenu);
	}
	
	/*
	 * Menu object update with data base updating
	 * MenuDTO with, at least, id field in order to update requested field in a json object. Example:
	 * @param json {"id": 1, "name": "food2"}
	 * @return a list with the updated menuDTO object
	 */
	@PostMapping(value = "/updateMenu")
	public ResponseEntity<ResponseMenu> updateMenu(@RequestBody MenuDTO menuDTO) {
		ResponseMenu responseMenu = new ResponseMenu();
		responseMenu.setSentOn(dateGenerator.generateCurrentDate());
		responseMenu.setTransactionId(UUID.randomUUID().toString());
		try {
            Menu currentMenu = menuService.updateMenu(menuDTO);
            List<Menu> updatedMenuList = new ArrayList<>();
            updatedMenuList.add(currentMenu);
            List<MenuDTO> updatedMenuDTO = updatedMenuList.stream().map(x -> new MenuDTO(x)).collect(Collectors.toList());

            responseMenu.setStatus("OK");
            responseMenu.setStatusCode("200");
            responseMenu.setMsg("Method updateMenu Success");
            responseMenu.setResValues(updatedMenuDTO);
            
        } catch (Exception e) {
            responseMenu.setStatus("NOK");
            responseMenu.setStatusCode("500");
            responseMenu.setMsg("Method updateMenu Error: " + e.getMessage());
            responseMenu.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseMenu);
	}
	
	/*
	 * Delete a menu by its id. Only persons with ADMIN role
	 * Integer id in a json object. Example:
	 * @param json {"id": 1}
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/deleteMenuById")
	public ResponseEntity<ResponseMenu> deleteMenu(@RequestBody MenuDTO menuDTO) {
		ResponseMenu responseMenu = new ResponseMenu();
		responseMenu.setSentOn(dateGenerator.generateCurrentDate());
		responseMenu.setTransactionId(UUID.randomUUID().toString());
		try {
            menuService.deleteMenu(menuDTO.getId());

            responseMenu.setStatus("OK");
            responseMenu.setStatusCode("200");
            responseMenu.setMsg("Method deleteMenu Success");
            responseMenu.setResValues(new ArrayList<>());
            
        } catch (Exception e) {
            responseMenu.setStatus("NOK");
            responseMenu.setStatusCode("500");
            responseMenu.setMsg("Method deleteMenu Error: " + e.getMessage());
            responseMenu.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseMenu);
	}
	
}
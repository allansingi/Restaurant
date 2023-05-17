package com.allanborges.restaurantAPI.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/api/v1")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	DateGenerator dateGenerator;
	
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
	
}
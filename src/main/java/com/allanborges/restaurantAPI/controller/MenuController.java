package com.allanborges.restaurantAPI.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allanborges.restaurantAPI.domain.Menu;
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
            responseMenu.setStatus("OK");
            responseMenu.setStatusCode("200");
            responseMenu.setMsg("Method getAllMenu Success");
            responseMenu.setResValues(list);
        } catch (Exception e) {
            responseMenu.setStatus("NOK");
            responseMenu.setStatusCode("500");
            responseMenu.setMsg("Method getActiveMenu Error: " + e.getMessage());
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
            responseMenu.setStatus("OK");
            responseMenu.setStatusCode("200");
            responseMenu.setMsg("Method getActiveMenu Success");
            responseMenu.setResValues(list);
        } catch (Exception e) {
            responseMenu.setStatus("NOK");
            responseMenu.setStatusCode("500");
            responseMenu.setMsg("Method getActiveMenu Error: " + e.getMessage());
            responseMenu.setResValues(new ArrayList<>());
        }
        return ResponseEntity.ok().body(responseMenu);
	}
	
}
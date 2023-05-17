package com.allanborges.restaurantAPI.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allanborges.restaurantAPI.domain.Client;
import com.allanborges.restaurantAPI.domain.Courier;
import com.allanborges.restaurantAPI.domain.Menu;
import com.allanborges.restaurantAPI.domain.Request;
import com.allanborges.restaurantAPI.domain.enums.PersonProfile;
import com.allanborges.restaurantAPI.domain.enums.RequestStatus;
import com.allanborges.restaurantAPI.repositories.CourierRepository;
import com.allanborges.restaurantAPI.repositories.MenuRepository;
import com.allanborges.restaurantAPI.repositories.PersonRepository;
import com.allanborges.restaurantAPI.repositories.RequestRepository;

@Service
public class DBService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private CourierRepository courierRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	public void instantiateDB() {
		
		Client cli1 = new Client(null, "Allan Borges", "123456789", "Null Street, 10" ,"allanborges@mail.com", "123");
		cli1.addProfile(PersonProfile.ADMIN);
		Client cli2 = new Client(null, "Scorpion", "111111111", "Scorpion Street, 1" ,"scorpion@mail.com", "123");
		Client cli3 = new Client(null, "Sub-Zero", "000000000", "Sub-Zero Street, -0" ,"sub-zero@mail.com", "123");
		Client cli4 = new Client(null, "Shang-Tsung", "999999999", "Shang-Tsung Street, 99" ,"shang-tsung@mail.com", "123");
		Client cli5 = new Client(null, "raiden", "222222222", "Raiden Street, 22" ,"raiden@mail.com", "123");
		
		Courier cou1 = new Courier(null, "Mario Bros", "987654321", "Mario Street, 10", "mario@mail.com", "123");
		Courier cou2 = new Courier(null, "Luigi Bros", "987321654", "Luigi Street, 20", "luigi@mail.com", "123");
		Courier cou3 = new Courier(null, "Toad", "111222333", "Toad Street, 30", "toad@mail.com", "123");
		Courier cou4 = new Courier(null, "Princess", "321123321", "Princess Street, 30", "princess@mail.com", "123");
		Courier cou5 = new Courier(null, "Wario", "999888777", "Wario Street, 40", "wario@mail.com", "123");
		
		Menu menu1 = new Menu(null, "Bitoque de carne", "Delicious portuguese dish of meat with fries", 4.9, 5, true, LocalDateTime.of(2023, 5, 9, 12, 00), null);
		Menu menu2 = new Menu(null, "Polvo à Lagareiro", "Octupus with tipical portuguese sauce and cooking", 14.9, 10, false, LocalDateTime.of(2023, 5, 20, 12, 00), null);
		Menu menu3 = new Menu(null, "Bacalhau à Lagareiro", "Cod Fish with tipical portuguese sauce and cooking", 12.9, 10, true, LocalDateTime.of(2023, 5, 18, 12, 00), null);
		Menu menu4 = new Menu(null, "Carabineiro", "Swrimp family sea food but more delicious", 199.9, 2, true, LocalDateTime.of(2023, 5, 25, 12, 00), null);
		Menu menu5 = new Menu(null, "Camarão Tigre", "Swrimp family sea food but bigger", 149.9, 2, true, LocalDateTime.of(2023, 5, 22, 12, 00), null);
		/*
		Request req1 = new Request(null, cli2.getAddress(), null, RequestStatus.ORDER_RECEIVED, cou1, cli2, Arrays.asList(menu1, menu2));
		Request req2 = new Request(null, cli3.getAddress(), null, RequestStatus.PREPARING, cou2, cli3, Arrays.asList(menu3));
		Request req3 = new Request(null, cli4.getAddress(), null, RequestStatus.READY, cou3, cli4, Arrays.asList(menu1, menu3, menu3));
		Request req4 = new Request(null, cli5.getAddress(), null, RequestStatus.IN_TRANSIT, cou4, cli5, Arrays.asList(menu3, menu3));
		Request req5 = new Request(null, cli1.getAddress(), LocalDate.of(2023, 5, 8), RequestStatus.DELIVERED, cou5, cli1, Arrays.asList(menu5, menu3, menu3));
		Request req6 = new Request(null, cli1.getAddress(), null, RequestStatus.CANCELLED, cou1, cli1, Arrays.asList(menu5, menu4, menu4));
		*/
		Request req1 = new Request(null, cli2.getAddress(), null, RequestStatus.ORDER_RECEIVED, null, cli2, 3, menu3.getName(), 5);
		Request req2 = new Request(null, cli3.getAddress(), null, RequestStatus.PREPARING, null, cli3, 3, menu3.getName(), 5);
		Request req3 = new Request(null, cli4.getAddress(), null, RequestStatus.READY, null, cli4, 4, menu4.getName(), 1);
		Request req4 = new Request(null, cli5.getAddress(), null, RequestStatus.IN_TRANSIT, cou1, cli5,  4, menu4.getName(), 1);
		Request req5 = new Request(null, cli1.getAddress(), LocalDate.of(2023, 5, 8), RequestStatus.DELIVERED, cou2, cli1, 5, menu5.getName(), 1);
		Request req6 = new Request(null, cli1.getAddress(), null, RequestStatus.CANCELLED, cou3, cli1, 5, menu5.getName(), 1);
		
		personRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
		courierRepository.saveAll(Arrays.asList(cou1, cou2, cou3, cou4, cou5));
		menuRepository.saveAll(Arrays.asList(menu1, menu2, menu3, menu4, menu5));
		requestRepository.saveAll(Arrays.asList(req1, req2, req3, req4, req5, req6));
		
	}

}
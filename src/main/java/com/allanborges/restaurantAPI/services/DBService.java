package com.allanborges.restaurantAPI.services;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

/*
 * Data base initial data for testing purpose
 */
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
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	public void instantiateDB() {
		
		Client cli1 = new Client(null, "Allan Borges", "123456789", "Null Street, 10" ,"allanborges@mail.com", encoder.encode("123"));
		cli1.addProfile(PersonProfile.ADMIN);
		Client cli2 = new Client(null, "Scorpion", "111111111", "Scorpion Street, 1" ,"scorpion@mail.com", encoder.encode("123"));
		Client cli3 = new Client(null, "Sub-Zero", "000000000", "Sub-Zero Street, -0" ,"sub-zero@mail.com", encoder.encode("123"));
		Client cli4 = new Client(null, "Shang-Tsung", "999999999", "Shang-Tsung Street, 99" ,"shang-tsung@mail.com", encoder.encode("123"));
		Client cli5 = new Client(null, "raiden", "222222222", "Raiden Street, 22" ,"raiden@mail.com", encoder.encode("123"));
		
		Courier cou1 = new Courier(null, "NOT_ASSIGNED", "555555555", "DATA_BASE_CLIENT", "restaurantapi@mail.com", encoder.encode("123"));
		Courier cou2 = new Courier(null, "Mario Bros", "987654321", "Mario Street, 10", "mario@mail.com", encoder.encode("123"));
		Courier cou3 = new Courier(null, "Luigi Bros", "987321654", "Luigi Street, 20", "luigi@mail.com", encoder.encode("123"));
		Courier cou4 = new Courier(null, "Toad", "111222333", "Toad Street, 30", "toad@mail.com", encoder.encode("123"));
		Courier cou5 = new Courier(null, "Princess", "321123321", "Princess Street, 30", "princess@mail.com", encoder.encode("123"));
		Courier cou6 = new Courier(null, "Wario", "999888777", "Wario Street, 40", "wario@mail.com", encoder.encode("123"));
		
		Menu menu1 = new Menu(null, "Bitoque de carne", "Delicious portuguese dish of meat with fries", 4.9, 5, true, LocalDateTime.of(2023, 5, 9, 12, 00), "https://www.bitoques.pt/assets/images/image03.jpg?v=7074c5aa");
		Menu menu2 = new Menu(null, "Polvo à Lagareiro", "Octupus with tipical portuguese sauce and cooking", 14.9, 10, false, LocalDateTime.of(2023, 5, 27, 12, 00), "https://www.petiscos.com/wp-content/uploads/2020/10/Polvo_Lagareiro_Receita-1-678x470.jpg");
		Menu menu3 = new Menu(null, "Bacalhau à Lagareiro", "Cod Fish with tipical portuguese sauce and cooking", 12.9, 10, true, LocalDateTime.of(2023, 12, 31, 12, 00), "https://receitadebacalhau.com.br/wp-content/uploads/2022/02/Receita-Tradicional-de-Bacalhau-a-Lagareiro.jpg");
		Menu menu4 = new Menu(null, "Carabineiro", "Swrimp family sea food but more delicious", 199.9, 2, true, LocalDateTime.of(2023, 12, 31, 12, 00), "https://www.receitasemenus.net/wp-content/uploads/2020/07/carabineiros-arroz-tinta-choco.jpg");
		Menu menu5 = new Menu(null, "Camarão Tigre", "Swrimp family sea food but bigger", 149.9, 2, true, LocalDateTime.of(2023, 12, 31, 12, 00), "https://ruralea.com/wp-content/uploads/2021/12/3omy67478ve61-1-1024x684.jpg");
		
		Request req1 = new Request(null, cli1, cou1, 3, 1, RequestStatus.ORDER_RECEIVED);
		Request req2 = new Request(null, cli1, cou1, 4, 1, RequestStatus.ORDER_RECEIVED);
		Request req3 = new Request(null, cli1, cou1, 5, 1, RequestStatus.ORDER_RECEIVED);
		Request req4 = new Request(null, cli2, cou1, 3, 1, RequestStatus.PREPARING);
		Request req5 = new Request(null, cli3, cou1, 4, 2, RequestStatus.READY);
		Request req6 = new Request(null, cli4, cou1, 5, 2, RequestStatus.READY);
		Request req7 = new Request(null, cli4, cou2, 5, 2, RequestStatus.IN_TRANSIT);
		Request req8 = new Request(null, cli4, cou3, 5, 2, RequestStatus.DELIVERED);
		Request req9 = new Request(null, cli5, cou5, 5, 2, RequestStatus.CANCELLED);
		
		personRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
		courierRepository.saveAll(Arrays.asList(cou1, cou2, cou3, cou4, cou5, cou6));
		menuRepository.saveAll(Arrays.asList(menu1, menu2, menu3, menu4, menu5));
		requestRepository.saveAll(Arrays.asList(req1, req2, req3, req4, req5, req6, req7, req8, req9));
		
	}

}
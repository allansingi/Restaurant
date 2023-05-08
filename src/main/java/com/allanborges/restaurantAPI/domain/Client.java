package com.allanborges.restaurantAPI.domain;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
	
	private List<Order> orders = new ArrayList<>();

	public Client() {
		super();
	}

	public Client(Integer id, String name, String nif, String address, String email, String password) {
		super(id, name, nif, address, email, password);
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
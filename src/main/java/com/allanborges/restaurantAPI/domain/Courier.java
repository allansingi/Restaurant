package com.allanborges.restaurantAPI.domain;

import java.util.ArrayList;
import java.util.List;

public class Courier extends Person {
	
	private List<Order> orders = new ArrayList<>();
	
	public Courier() {
		super();
	}

	public Courier(Integer id, String name, String nif, String address, String email, String password) {
		super(id, name, nif, address, email, password);
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
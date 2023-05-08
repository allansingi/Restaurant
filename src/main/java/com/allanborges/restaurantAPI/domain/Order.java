package com.allanborges.restaurantAPI.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import com.allanborges.restaurantAPI.domain.enums.OrderStatus;

public class Order {
	
	private Integer id;
	private String deliveryAddress;
	private LocalDate createDate= LocalDate.now();
	private LocalDate updateDate;
	private LocalDate deliveredDate;
	private OrderStatus orderStatus;
	
	private Client client;
	private Courier courier;
	
	private List<Menu> menuList;

	public Order() {
		super();
	}

	public Order(Integer id, String deliveryAddress, OrderStatus orderStatus, Client client, Courier courier, List<Menu> menuList) {
		super();
		this.id = id;
		this.deliveryAddress = deliveryAddress;
		this.orderStatus = orderStatus;
		this.client = client;
		this.courier = courier;
		this.menuList = menuList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public LocalDate getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}

	public LocalDate getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(LocalDate deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
}
package com.allanborges.restaurantAPI.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.allanborges.restaurantAPI.domain.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Request implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String deliveryAddress;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDate createDate= LocalDate.now();
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDate updateDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDate deliveredDate;
	private OrderStatus orderStatus;
	
	@ManyToOne
	@JoinColumn(name = "courier_id")
	private Courier courier;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@OneToMany(mappedBy = "request")
	private List<Menu> menus;

	public Request() {
		super();
	}

	public Request(Integer id, LocalDate deliveredDate, OrderStatus orderStatus, Courier courier, Client client,
			List<Menu> menus) {
		super();
		this.id = id;
		this.deliveredDate = deliveredDate;
		this.orderStatus = orderStatus;
		this.courier = courier;
		this.client = client;
		this.menus = menus;
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

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
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
		Request other = (Request) obj;
		return Objects.equals(id, other.id);
	}
	
}
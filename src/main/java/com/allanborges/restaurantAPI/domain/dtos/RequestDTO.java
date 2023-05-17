package com.allanborges.restaurantAPI.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.allanborges.restaurantAPI.domain.Client;
import com.allanborges.restaurantAPI.domain.Courier;
import com.allanborges.restaurantAPI.domain.Request;
import com.allanborges.restaurantAPI.domain.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

public class RequestDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String deliveryAddress;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDate createDate= LocalDate.now();
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDate updateDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDate deliveredDate;
	private RequestStatus requestStatus;
	
	private Courier courier;
	private Client client;
	private Integer requestedMenuId;
	private String requestedMenuName;
	private Integer requestedQuantity;

	public RequestDTO() {
		super();
	}

	public RequestDTO(Request request) {
		super();
		this.id = request.getId();
		this.deliveryAddress = request.getDeliveryAddress();
		this.deliveredDate = request.getDeliveredDate();
		this.requestStatus = request.getRequestStatus();
		this.courier = request.getCourier();
		this.client = request.getClient();
		this.requestedMenuId = request.getRequestedMenuId();
		this.requestedMenuName = request.getRequestedMenuName();
		this.requestedQuantity = request.getRequestedQuantity();
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

	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
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

	public Integer getRequestedMenuId() {
		return requestedMenuId;
	}

	public void setRequestedMenuId(Integer requestedMenuId) {
		this.requestedMenuId = requestedMenuId;
	}

	public String getRequestedMenuName() {
		return requestedMenuName;
	}

	public void setRequestedMenuName(String requestedMenuName) {
		this.requestedMenuName = requestedMenuName;
	}

	public Integer getRequestedQuantity() {
		return requestedQuantity;
	}

	public void setRequestedQuantity(Integer requestedQuantity) {
		this.requestedQuantity = requestedQuantity;
	}
	
}
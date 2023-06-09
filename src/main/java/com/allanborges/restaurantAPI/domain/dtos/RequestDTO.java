package com.allanborges.restaurantAPI.domain.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.allanborges.restaurantAPI.domain.Request;
import com.fasterxml.jackson.annotation.JsonFormat;

/*
 * DTO layer for Request Entity
 */
public class RequestDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer clientId;
	private String clientName;
	private String deliveryAddress;
	private Integer requestedMenuId;
	private String requestedMenuName;
	private Integer requestedQuantity;
	private Integer courierId;
	private String courierName;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime deliveredDate;
	
	private Integer requestStatus;

	public RequestDTO() {
		super();
	}

	public RequestDTO(Request request) {
		super();
		this.id = request.getId();
		this.clientId = request.getClient().getId();
		this.clientName = request.getClient().getName();
		this.requestedMenuId = request.getRequestedMenuId();
		this.requestedMenuName = request.getRequestedMenuName();
		this.requestedQuantity = request.getRequestedQuantity();
		this.deliveryAddress = request.getDeliveryAddress();
		this.courierId = request.getCourier().getId();
		this.courierName = request.getCourier().getName();
		this.createDate = request.getCreateDate();
		this.updateDate = request.getUpdateDate();
		this.deliveredDate = request.getDeliveredDate();
		this.requestStatus = request.getRequestStatus().getCode();
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

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public LocalDateTime getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(LocalDateTime deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public Integer getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(Integer requestStatus) {
		this.requestStatus = requestStatus;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Integer getCourierId() {
		return courierId;
	}

	public void setCourierId(Integer courierId) {
		this.courierId = courierId;
	}

	public String getCourierName() {
		return courierName;
	}

	public void setCourierName(String courierName) {
		this.courierName = courierName;
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
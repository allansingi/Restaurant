package com.allanborges.restaurantAPI.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.allanborges.restaurantAPI.domain.enums.RequestStatus;
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
	private RequestStatus requestStatus;
	
	@ManyToOne
	@JoinColumn(name = "courier_id")
	private Courier courier;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	private Integer requestedMenuId;
	private String requestedMenuName;
	private Integer requestedQuantity;

	public Request() {
		super();
	}

	public Request(Integer id, String deliveryAddress, LocalDate deliveredDate, RequestStatus requestStatus, Courier courier, Client client, Integer requestedMenuId, String requestedMenuName, Integer requestedQuantity) {
		super();
		this.id = id;
		this.deliveryAddress = deliveryAddress;
		this.deliveredDate = deliveredDate;
		this.requestStatus = requestStatus;
		this.courier = courier;
		this.client = client;
		this.requestedMenuId = requestedMenuId;
		this.requestedMenuName = requestedMenuName;
		this.requestedQuantity = requestedQuantity;
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
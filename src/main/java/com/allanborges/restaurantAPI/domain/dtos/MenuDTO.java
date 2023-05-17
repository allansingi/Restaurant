package com.allanborges.restaurantAPI.domain.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.allanborges.restaurantAPI.domain.Menu;
import com.allanborges.restaurantAPI.domain.Request;
import com.fasterxml.jackson.annotation.JsonFormat;

public class MenuDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private Integer quantity;
	private Boolean active;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime expireDate;
	
	private String imageUrl;
	private Request request;
	
	public MenuDTO() {
		super();
	}

	public MenuDTO(Menu menu) {
		super();
		this.id = menu.getId();
		this.name = menu.getName();
		this.description = menu.getDescription();
		this.price = menu.getPrice();
		this.quantity = menu.getQuantity();
		this.active = menu.getActive();
		this.expireDate = menu.getExpireDate();
		this.imageUrl = menu.getImageUrl();
		this.request = menu.getRequest();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public LocalDateTime getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDateTime expireDate) {
		this.expireDate = expireDate;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

}
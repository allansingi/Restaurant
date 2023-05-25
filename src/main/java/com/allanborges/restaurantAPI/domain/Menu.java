package com.allanborges.restaurantAPI.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.allanborges.restaurantAPI.domain.dtos.MenuDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private Integer quantity;
	private Boolean active;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime expireDate;
	private String imageUrl;
	
	public Menu() {
		super();
	}

	public Menu(Integer id, String name, String description, Double price, Integer quantity, Boolean active, LocalDateTime expireDate, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.active = active;
		this.expireDate = expireDate;
		this.imageUrl = imageUrl;
	}
	
	public Menu(MenuDTO menuDTO) {
		super();
		this.id = menuDTO.getId();
		this.name = menuDTO.getName();
		this.description = menuDTO.getDescription();
		this.price = menuDTO.getPrice();
		this.quantity = menuDTO.getQuantity();
		this.active = menuDTO.getActive();
		this.expireDate = menuDTO.getExpireDate();
		this.imageUrl = menuDTO.getImageUrl();
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
	
}
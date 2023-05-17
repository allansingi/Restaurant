package com.allanborges.restaurantAPI.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.allanborges.restaurantAPI.domain.dtos.CourierDTO;
import com.allanborges.restaurantAPI.domain.enums.PersonProfile;

@Entity
public class Courier extends Person implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "courier")
	private List<Request> requests = new ArrayList<>();
	
	public Courier() {
		super();
		addProfile(PersonProfile.CLIENT);
	}

	public Courier(Integer id, String name, String nif, String address, String email, String password) {
		super(id, name, nif, address, email, password);
		addProfile(PersonProfile.CLIENT);
	}
	
	public Courier(CourierDTO courierDTO) {
		super();
		this.id = courierDTO.getId();
		this.name = courierDTO.getName();
		this.nif = courierDTO.getNif();
		this.address = courierDTO.getAddress();
		this.email = courierDTO.getEmail();
		this.password = courierDTO.getPassword();
		this.profiles = courierDTO.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
		this.createDate = courierDTO.getCreateDate();
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setOrders(List<Request> requests) {
		this.requests = requests;
	}
	
}
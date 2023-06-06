package com.allanborges.restaurantAPI.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.allanborges.restaurantAPI.domain.dtos.ClientDTO;
import com.allanborges.restaurantAPI.domain.enums.PersonProfile;

/*
 * Client Entity class with all business logic requested fields
 */
@Entity
public class Client extends Person implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "client")
	private List<Request> requests = new ArrayList<>();

	public Client() {
		super();
		addProfile(PersonProfile.CLIENT);
	}

	public Client(Integer id, String name, String nif, String address, String email, String password) {
		super(id, name, nif, address, email, password);
		addProfile(PersonProfile.CLIENT);
	}
	
	public Client(ClientDTO clientDTO) {
		super();
		this.id = clientDTO.getId();
		this.name = clientDTO.getName();
		this.nif = clientDTO.getNif();
		this.address = clientDTO.getAddress();
		this.email = clientDTO.getEmail();
		this.password = clientDTO.getPassword();
		this.profiles = clientDTO.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
		this.createDate = clientDTO.getCreateDate();
	}

	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}
	
}
package com.allanborges.restaurantAPI.domain.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.allanborges.restaurantAPI.domain.Client;
import com.allanborges.restaurantAPI.domain.enums.PersonProfile;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ClientDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Integer id;
	protected String name;
	protected String nif;
	protected String address;
	protected String email;
	protected String password;
	protected Set<Integer> profiles = new HashSet<>();
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected LocalDateTime createDate = LocalDateTime.now();

	public ClientDTO() {
		super();
	}

	public ClientDTO(Client client) {
		super();
		this.id = client.getId();
		this.name = client.getName();
		this.nif = client.getNif();
		this.address = client.getAddress();
		this.email = client.getEmail();
		this.password = client.getPassword();
		this.profiles = client.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
		this.createDate = client.getCreateDate();
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

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<PersonProfile> getProfiles() {
		return profiles.stream().map(x -> PersonProfile.toEnum(x)).collect(Collectors.toSet());
	}

	public void setProfiles(Set<Integer> profiles) {
		this.profiles = profiles;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
}
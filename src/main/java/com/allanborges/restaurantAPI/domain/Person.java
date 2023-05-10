package com.allanborges.restaurantAPI.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.allanborges.restaurantAPI.domain.enums.PersonProfile;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public abstract class Person implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	protected String name;
	
	@Column(unique = true)
	protected String nif;
	
	@Column(unique = true)
	protected String address;
	protected String email;
	protected String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PROFILES")
	protected Set<Integer> profiles = new HashSet<>();
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected LocalDateTime createDate = LocalDateTime.now();
	
	public Person() {
		super();
		addProfile(PersonProfile.CLIENT);
	}

	public Person(Integer id, String name, String nif, String address, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.nif = nif;
		this.address = address;
		this.email = email;
		this.password = password;
		addProfile(PersonProfile.CLIENT);
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

	public void addProfile(PersonProfile profile) {
		this.profiles.add(profile.getCode());
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nif);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(id, other.id) && Objects.equals(nif, other.nif);
	}
	
}
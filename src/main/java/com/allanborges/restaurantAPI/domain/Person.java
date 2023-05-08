package com.allanborges.restaurantAPI.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.allanborges.restaurantAPI.domain.enums.PersonProfile;

public abstract class Person {
	
	protected Integer id;
	protected String name;
	protected String nif;
	protected String address;
	protected String email;
	protected String password;
	protected Set<Integer> profiles = new HashSet<>();
	protected LocalDate createDate = LocalDate.now();
	
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

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
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
package com.example.app.group;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="drustvo")
public class Group {
	
	@Id
	@Column(name="IdDrustva")
	private Integer id;
	
	@Column(name="RegistarskiBroj")
	private String registrationNumber;
	
	@Column(name="Naziv")
	private String name;
	
	@Column(name="Opis",length=500)
	private String description;
	
	@Column(name="Lokacija")
	private String location;
	
	@Column(name="Kontakt")
	private String contact;
	
	@Column(name="BrojClanova")
	private Integer membersCount;
	
	@Column(name="DatumOsnivanja")
	private LocalDate date;
	
	@Column(name="is_fishing_group",nullable = true)
	private Boolean isFishingGroup;
	
	public Group(Integer id, String registrationNumber, String name, String description, String location,
			String contact, Integer membersCount, LocalDate date) {
		super();
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.name = name;
		this.description = description;
		this.location = location;
		this.contact = contact;
		this.membersCount = membersCount;
		this.date = date;
	}
	
	public Group()
	{
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getMembersCount() {
		return membersCount;
	}

	public void setMembersCount(Integer membersCount) {
		this.membersCount = membersCount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Boolean getIsFishingGroup() {
		return isFishingGroup;
	}

	public void setIsFishingGroup(Boolean isFishingGroup) {
		this.isFishingGroup = isFishingGroup;
	}
	
	
}

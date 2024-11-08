package com.example.app.animal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="zivotinja")
public class Animal {

	
	@Id
	@Column(name="naziv")
	private String name;
	
	@Column(name="je_riba")
	private Boolean isFish;
	
	public Animal()
	{
		super();
	}

	public Animal(String name, Boolean isFish) {
		super();
		this.name = name;
		this.isFish = isFish;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsFish() {
		return isFish;
	}

	public void setIsFish(Boolean isFish) {
		this.isFish = isFish;
	}
	
	
	
}

package com.example.app.fisherman;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ribolovac")
public class Fisherman {
	
	@Id
	@Column(name="id_korisnika")
	private Integer id;
	
	@Column(name="id_drustva")
	private Integer idGroup;
	
	
	public Fisherman()
	{
		super();
	}


	public Fisherman(Integer id, Integer idGroup) {
		super();
		this.id = id;
		this.idGroup = idGroup;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getIdGroup() {
		return idGroup;
	}


	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}
	
}

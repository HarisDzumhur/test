package com.example.app.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="korisnik")
public class User {

	
	@Id
	@Column(name="id_korisnika")
	private Integer idUser;
	
	public User()
	{
		super();
	}
	
	public User(Integer idUser) {
		super();
		this.idUser = idUser;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	
	
}

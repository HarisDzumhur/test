package com.example.app.administrator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="administrator")
public class Administrator {
	@Id
	@Column(name="KorisnickoIme")
	private String username;
	
	@Column(name="Lozinka")
	private String password;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Ime")
	private String name;
	
	@Column(name="Prezime")
	private String surname;
	
	public Administrator(String username, String password, String email, String name, String surname) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.surname = surname;
	}
	
	public Administrator()
	{}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
}

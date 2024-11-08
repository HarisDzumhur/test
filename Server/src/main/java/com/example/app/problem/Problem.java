package com.example.app.problem;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="problem")
public class Problem {
	
	@Id
	@Column(name="IdProblema")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="Opis",length=1000)
	private String description;
	
	@Column(name="DatumKreiranja")
	private LocalDateTime date;
	
	@Column(name="IdKlijenta")
	private Integer idClient;
	
	@Column(name="Rijesen")
	private Boolean solved;
	
	public Problem() {
		
	}
	@Column(name="user_name",nullable=true)
	private String userName;
	@Column(name="user_surname",nullable=true)
	private String userSurname;

	public Problem(Integer id, String description, Integer idClient, Boolean solved, LocalDateTime date,String userName,String userSurname) {
		super();
		this.id = id;
		this.description = description;
		this.idClient = idClient;
		this.solved = solved;
		this.date = date;
		this.userName=userName;
		this.userSurname=userSurname;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	@Override
	public String toString() {
		return "Problem [id=" + id + ", description=" + description + ", date=" + date + ", idClient=" + idClient
				+ ", solved=" + solved + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public Boolean getSolved() {
		return solved;
	}

	public void setSolved(Boolean solved) {
		this.solved = solved;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}

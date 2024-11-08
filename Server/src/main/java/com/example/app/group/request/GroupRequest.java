package com.example.app.group.request;

import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="zahtjev_za_uclanjivanje")
public class GroupRequest {

	@Id 
	@Column(name="id_zahtjeva")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="id_drustva")
	private Integer idGroup;
	
	@Column(name="id_korisnika")
	private Integer idUser;
	@Column(name="datum")
	private LocalDateTime date;
	
	private String userName;
	private String userSurname;
	
	@Column(name="user_image",length=16777215)
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] userImage;
	
	
	public GroupRequest(Integer id, Integer idGroup, Integer idUser,LocalDateTime date) {
		super();
		this.id = id;
		this.idGroup = idGroup;
		this.idUser = idUser;
	}

	public GroupRequest() {
		super();
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

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
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

	public byte[] getUserImage() {
		return userImage;
	}

	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
	
}

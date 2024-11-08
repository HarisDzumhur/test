package com.example.app.forum;

import java.time.LocalDateTime;

import org.hibernate.annotations.GeneratorType;

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
@Table(name="tema_forum")
public class Forum {
	
	
	@Id
	@Column(name="id_teme")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="naziv")
	private String title;
	
	@Column(name="id_klijenta")
	private Integer idClient;
	
	@Column(name="Slika",length=16777215)
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;
	
	@Column(name="datum")
	private LocalDateTime date;
	
	@Column(name="opis")
	private String description;
	
	
	
	private String userName;
	private String userSurname;
	
	@Column(name="userImage",length=16777215)
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] userImage;
	
	private Integer replyNumber;
	
	
	public Forum()
	{
		super();
	}
	
	

	public Forum(Integer id, String title, Integer idClient, byte[] image,LocalDateTime date,String description) {
		super();
		this.id = id;
		this.title = title;
		this.idClient = idClient;
		this.image = image;
		this.date=date;
		this.description=description;
	}



	public LocalDateTime getDate() {
		return date;
	}



	public void setDate(LocalDateTime date) {
		this.date = date;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Integer getReplyNumber() {
		return replyNumber;
	}



	public void setReplyNumber(Integer replyNumber) {
		this.replyNumber = replyNumber;
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



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	

}

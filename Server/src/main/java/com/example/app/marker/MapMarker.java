package com.example.app.marker;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="oznaka")
public class MapMarker {

	@Id
	@Column(name="id_oznake")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="id_tipa")
	private Integer idType;
	
	
	@Column(name="x")
	private Double x;
	
	@Column(name="y")
	private Double y;
	
	@Column(name="id_korisnika")
	private Integer idUser;
	
	@Column(name="Opis")
	private String description;
	
	@Column(name="datum_postavljanja")
	private LocalDateTime date;
	
	@Column(name="je_javna")
	private Boolean isPublic;
	
	private String userName;
	private String userSurname;
	
	

	
	public MapMarker(Integer id, Integer idType, Double x, Double y, Integer idUser, String description,
			LocalDateTime date, Boolean isPublic) {
		super();
		this.id = id;
		this.idType = idType;
		this.x = x;
		this.y = y;
		this.idUser = idUser;
		this.description = description;
		this.date = date;
		this.isPublic = isPublic;
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

	public MapMarker() {
		super();
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdType() {
		return idType;
	}

	public void setIdType(Integer idType) {
		this.idType = idType;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	
	
	
	
}

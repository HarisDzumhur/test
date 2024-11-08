package com.example.app.trophy;

import java.time.LocalDateTime;
import java.util.Objects;

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
@Table(name="trofej")
public class Trophy {
	
	@Id
	@Column(name="IdTrofeja")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="Naziv")
	private String name;
	
	@Column(name="DatumUlova")
	private LocalDateTime date;
	
	@Column(name="Opis")
	private String description;
	
	@Column(name="Slika",length=16777215)
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;
	
	@Column(name="IdKorisnik")
	private Integer idClient;
	
	@Column(name="NazivZivotinje")
	private String animal;
	
	@Column(name="JeJavni")
	private Boolean isPublic;
	
	@Column(name="id_konfiguracije")
	private Integer idConfiguration;
	
	@Column(name="lokacija_lat")
	private Double locationLat;
	
	@Column(name="lokacija_lng")
	private Double locationLng;
	
	@Column(name="datum_objave")
	private LocalDateTime publishDate;
	
	private String userName;
	private String userSurname;
	private Boolean isFish;
	
	@Column(name="userImage",length=16777215)
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] userImage;
	
	
	public Trophy() {
		
	}
	
	
	public Trophy(Integer id)
	{
		this.id=id;
	}
	public Trophy(Integer id, String name, LocalDateTime date, String description, byte[] image, Integer idClient,
			String animal, Boolean isPublic, Integer idConfiguration, Double locationLat, Double locationLng,LocalDateTime publishDate) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.description = description;
		this.image = image;
		this.idClient = idClient;
		this.animal = animal;
		this.isPublic = isPublic;
		this.idConfiguration = idConfiguration;
		this.locationLat = locationLat;
		this.locationLng = locationLng;
		this.publishDate=publishDate;
	}



	public Boolean getIsFish() {
		return isFish;
	}


	public void setIsFish(Boolean isFish) {
		this.isFish = isFish;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Integer getIdClient() {
		return idClient;
	}

	public void setIdClient(Integer idClient) {
		this.idClient = idClient;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}



	public Integer getIdConfiguration() {
		return idConfiguration;
	}



	public void setIdConfiguration(Integer idConfiguration) {
		this.idConfiguration = idConfiguration;
	}



	public Double getLocationLat() {
		return locationLat;
	}



	public void setLocationLat(Double locationLat) {
		this.locationLat = locationLat;
	}



	public Double getLocationLng() {
		return locationLng;
	}



	public void setLocationLng(Double locationLng) {
		this.locationLng = locationLng;
	}



	public LocalDateTime getPublishDate() {
		return publishDate;
	}


	public void setPublishDate(LocalDateTime publishDate) {
		this.publishDate = publishDate;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trophy other = (Trophy) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}

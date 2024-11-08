package com.example.app.event;

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
@Table(name="dogadjaj")
public class Event {
	
	@Id
	@Column(name="idDogadjaja")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="Naziv")
	private String name;
	
	@Column(name="Datum")
	private LocalDateTime date;
	
	@Column(name="Opis")
	private String description;
	
	@Column(name="Slika",length=16777215)
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;
	
	@Column(name="IdDrustva")
	private Integer idGroup;
	
	@Column(name="lokacija_lat")
	private Double latitude;
	
	@Column(name="lokacija_lng")
	private Double longitude;
	
	private String groupName;
	
	@Column(name="slika_drustva",length=16777215)
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] groupImage;
	
	
	
	public Event(Integer id, String name, LocalDateTime date, String description, byte[] image, Integer idGroup,Double latitude,Double longitude) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.description = description;
		this.image = image;
		this.idGroup = idGroup;
		this.latitude=latitude;
		this.longitude=longitude;
	}
	
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public byte[] getGroupImage() {
		return groupImage;
	}

	public void setGroupImage(byte[] groupImage) {
		this.groupImage = groupImage;
	}

	public Event() {
		
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

	public Integer getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	
}

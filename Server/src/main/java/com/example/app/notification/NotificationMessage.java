package com.example.app.notification;

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
@Table(name="obavjestenje")
public class NotificationMessage {
	
	@Id
	@Column(name="idObavjestenja")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="Sadrzaj")
	private String content;
	
	@Column(name="idDrustva")
	private Integer idGroup;
	
	@Column(name="Datum")
	private LocalDateTime date;
	
	@Column(name="idKlijent")
	private Integer idClient;
	
	@Column(name="slika",length=16777215)
	 @Lob
	 @Basic(fetch = FetchType.LAZY)
	private byte[] image;
	
	@Column(name="je_dogadjaj")
	private Boolean isEvent;
	
	@Column(name="id_dogadjaja",nullable = true)
	private Integer idEvent;
	
	
	@Column(name="je_staro")
	private Boolean isOld;

	@Column(name="slika_grupe",length=16777215)
	 @Lob
	 @Basic(fetch = FetchType.LAZY)
	private byte[] groupImage;
	
	@Column(name="ime_grupe")
	private String groupName;
	
	public NotificationMessage(Integer id, String content, Integer idGroup, LocalDateTime date, Integer idClient,byte[] image,Boolean isEvent,Integer idEvent,
			Boolean isOld) {
		super();
		this.id = id;
		this.content = content;
		this.idGroup = idGroup;
		this.date = date;
		this.idClient = idClient;
		this.image=image;
		this.isEvent=isEvent;
		this.idEvent=idEvent;
		this.isOld=isOld;
	}
	
	public NotificationMessage()
	{
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getIdGroup() {
		return idGroup;
	}
	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
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

	public Boolean getIsEvent() {
		return isEvent;
	}

	public void setIsEvent(Boolean isEvent) {
		this.isEvent = isEvent;
	}

	public Integer getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}

	public byte[] getGroupImage() {
		return groupImage;
	}

	public void setGroupImage(byte[] groupImage) {
		this.groupImage = groupImage;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Boolean getIsOld() {
		return isOld;
	}

	public void setIsOld(Boolean isOld) {
		this.isOld = isOld;
	}
	
	
}

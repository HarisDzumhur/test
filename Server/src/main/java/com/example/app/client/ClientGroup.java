package com.example.app.client;

import java.time.LocalDate;

public class ClientGroup {
	
	private Integer id;
	private String password;
	private String mail;
	private String name;
	private String surname;
	private byte[] image;
	private Boolean blocked;
	private Boolean deleted;
	private Boolean google_signin;
	private String registrationNumber;
	private String groupName;
	private String description;
	private String location;
	private String contact;
	private Integer membersCount;
	private LocalDate date;
	private Boolean isFishingGroup;
	private Integer followCount;
	public ClientGroup() {
		super();
	}
	public ClientGroup(Integer id, String password, String mail, String name, String surname, byte[] image,
			Boolean blocked, Boolean deleted, Boolean google_signin, String registrationNumber, String groupName,
			String description, String location, String contact, Integer membersCount, LocalDate date,Boolean isFishingGroup,Integer followCount) {
		super();
		this.id = id;
		this.password = password;
		this.mail = mail;
		this.name = name;
		this.surname = surname;
		this.image = image;
		this.blocked = blocked;
		this.deleted = deleted;
		this.google_signin = google_signin;
		this.registrationNumber = registrationNumber;
		this.groupName = groupName;
		this.description = description;
		this.location = location;
		this.contact = contact;
		this.membersCount = membersCount;
		this.date = date;
		this.isFishingGroup=isFishingGroup;
		this.followCount=followCount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
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
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Boolean getBlocked() {
		return blocked;
	}
	public void setBlocked(Boolean blocked) {
		this.blocked = blocked;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public Boolean getGoogle_signin() {
		return google_signin;
	}
	public void setGoogle_signin(Boolean google_signin) {
		this.google_signin = google_signin;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Integer getMembersCount() {
		return membersCount;
	}
	public void setMembersCount(Integer membersCount) {
		this.membersCount = membersCount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Boolean getIsFishingGroup() {
		return isFishingGroup;
	}
	public void setIsFishingGroup(Boolean isFishingGroup) {
		this.isFishingGroup = isFishingGroup;
	}
	public Integer getFollowCount() {
		return followCount;
	}
	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}
	
	

}

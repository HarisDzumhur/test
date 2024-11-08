package com.example.app.client;


public class RegisterGoogleInformation {
	private String name;
	private String mail;
	private String surname;
	private String picture;
	
	public RegisterGoogleInformation()
	{
		
	}
	
	

	public RegisterGoogleInformation(String name, String mail, String surname, String picture) {
		super();
		this.name = name;
		this.mail = mail;
		this.surname = surname;
		this.picture = picture;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}

package com.example.app.client;

public class SimpleClient {
	
	private Integer id;
	private String name;
	private String surname;
	private byte[] image;
	
	
	public SimpleClient()
	{
		super();
	}
	
	
	public SimpleClient(Integer id, String name, String surname, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.image = image;
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
	
	
	
	

}

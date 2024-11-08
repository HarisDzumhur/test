package com.example.app.calendar;

import java.io.Serializable;

class HuntingCalendarId implements Serializable {
    private Integer year;
    private String animal;
    public HuntingCalendarId() {super();};
	public HuntingCalendarId(Integer year, String animal) {
		super();
		this.year = year;
		this.animal = animal;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}  
}

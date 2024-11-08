package com.example.app.calendar;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;



@Entity
@Table(name="lovni_kalendar")
@IdClass(HuntingCalendarId.class)
public class HuntingCalendar {

	@Id
	@Column(name="Godina")
	private Integer year;
	
	@Id
	@Column(name="naziv_zivotinje")
	private String animal;
	
	@Column(name="Od")
	private LocalDate dateFrom;
	
	
	@Column(name="Do")
	private LocalDate dateTo;
	
	@Column(name="Napomena",length=500)
	private String note;
	
	public HuntingCalendar()
	{
		super();
	}

	public HuntingCalendar(Integer year, String animal, LocalDate dateFrom, LocalDate dateTo, String note) {
		super();
		this.year = year;
		this.animal = animal;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.note = note;
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

	public LocalDate getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public LocalDate getDateTo() {
		return dateTo;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}

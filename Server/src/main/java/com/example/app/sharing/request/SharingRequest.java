package com.example.app.sharing.request;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="zahtjev_za_dijeljenje")
@IdClass(SharingRequestId.class)
public class SharingRequest {

	@Id
	@Column(name="id_grupe")
	String idGroup;
	
	@Id
	@Column(name="od_id_korisnika")
	private Integer idUser1;
	
	@Id
	@Column(name="za_id_korisnika")
	private Integer idUser2;

	@Column(name="datum_slanja")
	private LocalDateTime date;
	
	public SharingRequest() {
		super();
	}

	public SharingRequest(String idGroup,Integer idUser1, Integer idUser2,LocalDateTime date) {
		super();
		this.idGroup=idGroup;
		this.idUser1 = idUser1;
		this.idUser2 = idUser2;
		this.date=date;
	}

	public String getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(String idGroup) {
		this.idGroup = idGroup;
	}

	public Integer getIdUser1() {
		return idUser1;
	}

	public void setIdUser1(Integer idUser1) {
		this.idUser1 = idUser1;
	}

	public Integer getIdUser2() {
		return idUser2;
	}

	public void setIdUser2(Integer idUser2) {
		this.idUser2 = idUser2;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	
	
}

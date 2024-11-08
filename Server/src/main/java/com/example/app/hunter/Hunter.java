package com.example.app.hunter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="lovac")
public class Hunter {

	@Id
	@Column(name="id_korisnika")
	private Integer idUser;
	
	@Column(name="id_sekcije")
	private Integer idSection;
	
	@Column(name="id_drustva")
	private Integer idGroup;
	
	public Hunter() {
		super();
	}

	public Hunter(Integer idUser, Integer idSection,Integer idGroup) {
		super();
		this.idUser = idUser;
		this.idSection = idSection;
		this.idGroup=idGroup;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdSection() {
		return idSection;
	}

	public void setIdSection(Integer idSection) {
		this.idSection = idSection;
	}

	public Integer getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}
	
	
	
}

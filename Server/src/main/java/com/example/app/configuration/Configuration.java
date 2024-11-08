package com.example.app.configuration;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="konfiguracija")
public class Configuration {

	@Id
	@Column(name="idkonfiguracije")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name="naziv_konfiguracije")
	private String name;
	
	@Column(name="id_korisnika")
	private Integer idUser;
	
	@Column(name="obrisana")
	private Boolean deleted;

	public Configuration() {
		super();
	}

	public Configuration(Integer id, String name, Integer idUser,Boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.idUser = idUser;
		this.deleted=deleted;
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

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
}

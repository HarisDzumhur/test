package com.example.app.location;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="trenutna_lokacija")
public class Location {

	@Id
	@Column(name="id_korisnika")
	private Integer idUser;
	
	@Column(name="x")
	private Double x;
	
	@Column(name="y")
	private Double y;
	
	
	
}

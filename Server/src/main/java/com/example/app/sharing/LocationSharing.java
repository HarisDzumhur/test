package com.example.app.sharing;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="dijeljenje_lokacije")
@IdClass (LocationSharingId.class)
public class LocationSharing {

	@Id
	@Column(name="Lovac1")
	private Integer idHunter1;
	
	@Id
	@Column(name="Lovac2")
	private Integer idHunter2;
	
	

	public LocationSharing() {
		super();
	}



	public LocationSharing(Integer idHunter1, Integer idHunter2) {
		super();
		this.idHunter1 = idHunter1;
		this.idHunter2 = idHunter2;
	}



	public Integer getIdHunter1() {
		return idHunter1;
	}

	public void setIdHunter1(Integer idHunter1) {
		this.idHunter1 = idHunter1;
	}

	public Integer getIdHunter2() {
		return idHunter2;
	}

	public void setIdHunter2(Integer idHunter2) {
		this.idHunter2 = idHunter2;
	}
	
	

}

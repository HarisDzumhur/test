package com.example.app.sharing;

import java.io.Serializable;

public class LocationSharingId implements Serializable {
	
	private Integer idHunter1;
	private Integer idHunter2;
	
	public LocationSharingId() {super();}
	
	public LocationSharingId(Integer idHunter1,Integer idHunter2)
	{
		super();
		this.idHunter1=idHunter1;
		this.idHunter2=idHunter2;
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

package com.example.app.sharing.request;

import java.io.Serializable;

public class SharingRequestId implements Serializable {
	
	private String idGroup;
	private Integer idUser1;
	private Integer idUser2;
	public SharingRequestId() {
		super();
	}
	
	public SharingRequestId(String idGroup,Integer idUser1, Integer idUser2) {
		super();
		this.idGroup=idGroup;
		this.idUser1 = idUser1;
		this.idUser2 = idUser2;
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
	
	
	
}

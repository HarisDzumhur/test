package com.example.app.following;

import java.io.Serializable;

public class FollowingId implements Serializable {

	private Integer idGroup;
	private Integer idUser;
	
	
	public FollowingId(Integer idGroup, Integer idUser) {
		super();
		this.idGroup = idGroup;
		this.idUser = idUser;
	}
	
	
	
	public FollowingId() {
		super();
	}



	public Integer getIdGroup() {
		return idGroup;
	}
	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	
	
	
	
	
}

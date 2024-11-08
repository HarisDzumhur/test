package com.example.app.following;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="pracenje")
@IdClass(FollowingId.class)
public class Following {

	@Id
	@Column(name="id_korisnika")
	private Integer idUser;
	@Id
	@Column(name="id_drustva")
	private Integer idGroup;
	
	
	
	public Following(Integer idUser, Integer idGroup) {
		super();
		this.idUser = idUser;
		this.idGroup = idGroup;
	}

	public Following() {
		super();
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}

	
	
	
}

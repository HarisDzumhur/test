package com.example.app.userequipment;

import java.io.Serializable;

public class UserEquipmentId implements Serializable {
	private Integer idEquipment;
	private Integer idConfiguration;
	
	public UserEquipmentId() {
		super();
	}

	public UserEquipmentId(Integer idEquipment, Integer idConfiguration) {
		super();
		this.idEquipment = idEquipment;
		this.idConfiguration = idConfiguration;
	}

	public Integer getIdEquipment() {
		return idEquipment;
	}

	public void setIdEquipment(Integer idEquipment) {
		this.idEquipment = idEquipment;
	}

	public Integer getIdConfiguration() {
		return idConfiguration;
	}

	public void setIdConfiguration(Integer idConfiguration) {
		this.idConfiguration = idConfiguration;
	}


	
	
}

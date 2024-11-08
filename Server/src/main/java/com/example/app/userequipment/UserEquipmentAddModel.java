package com.example.app.userequipment;

import java.util.List;


public class UserEquipmentAddModel {
	
	Integer idUser;
	String configurationName;
	List<Integer> equipmentIds;
	
	public UserEquipmentAddModel() {
		super();
	}

	public UserEquipmentAddModel(Integer idUser, String configurationName, List<Integer> equipmentIds) {
		super();
		this.idUser = idUser;
		this.configurationName = configurationName;
		this.equipmentIds = equipmentIds;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getConfigurationName() {
		return configurationName;
	}

	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}

	public List<Integer> getEquipmentIds() {
		return equipmentIds;
	}

	public void setEquipmentIds(List<Integer> equipmentIds) {
		this.equipmentIds = equipmentIds;
	}
	
	
	

}

package com.example.app.equipment;

import java.util.List;

public class EquipmentList {

	private List<Integer> equipmentIds;

	public EquipmentList() {
		super();
	}
	
	public EquipmentList(List<Integer> equipmentIds) {
		super();
		this.equipmentIds = equipmentIds;
	}

	public List<Integer> getEquipmentIds() {
		return equipmentIds;
	}

	public void setEquipmentIds(List<Integer> equipmentIds) {
		this.equipmentIds = equipmentIds;
	}
	
}

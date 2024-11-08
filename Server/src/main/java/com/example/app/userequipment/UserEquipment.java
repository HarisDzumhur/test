package com.example.app.userequipment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name="oprema_korisnika")
@IdClass(UserEquipmentId.class)
public class UserEquipment {
	
	@Id
	@Column(name="oprema_id_opreme")
	private Integer idEquipment;
	
	@Id
	@Column(name="idkonfiguracije")
	private Integer idConfiguration;
	
	
	private Integer idEquipmentType;
	private String configurationName;
	private String equipmentName;

	public UserEquipment()
	{
		
	}

	public UserEquipment(Integer idEquipment,Integer idConfiguration) {
		super();
		this.idEquipment = idEquipment;
		this.idConfiguration=idConfiguration;
	}



	public Integer getIdEquipment() {
		return idEquipment;
	}

	public void setIdEquipment(Integer idEquipment) {
		this.idEquipment = idEquipment;
	}
	public Integer getConfigurationNumber() {
		return idConfiguration;
	}

	public void setConfigurationNumber(Integer idConfiguration) {
		this.idConfiguration = idConfiguration;
	}
	public Integer getIdEquipmentType() {
		return idEquipmentType;
	}

	public void setIdEquipmentType(Integer idEquipmentType) {
		this.idEquipmentType = idEquipmentType;
	}

	public Integer getIdConfiguration() {
		return idConfiguration;
	}

	public void setIdConfiguration(Integer idConfiguration) {
		this.idConfiguration = idConfiguration;
	}

	public String getConfigurationName() {
		return configurationName;
	}

	public void setConfigurationName(String configurationName) {
		this.configurationName = configurationName;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	
	
}

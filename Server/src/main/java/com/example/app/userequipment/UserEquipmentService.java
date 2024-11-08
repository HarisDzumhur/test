package com.example.app.userequipment;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.configuration.Configuration;
import com.example.app.configuration.ConfigurationRepository;
import com.example.app.equipment.Equipment;
import com.example.app.equipment.EquipmentRepository;


@Service
public class UserEquipmentService {
	private final UserEquipmentRepository uer;
	private final EquipmentRepository eqr;
	private final ConfigurationRepository rpConfig;
	
	
	@Autowired
	public UserEquipmentService(UserEquipmentRepository uer,EquipmentRepository eqr,ConfigurationRepository rpConfig)
	{
		this.uer = uer;
		this.eqr=eqr;
		this.rpConfig=rpConfig;
	}

	public List<UserEquipment> getAll(Integer idClient) {
		List<UserEquipment> list=uer.findAll();
		List<UserEquipment> newList=new ArrayList<UserEquipment>();
		for(UserEquipment eq:list)
		{
			Configuration conf=rpConfig.findById(eq.getConfigurationNumber()).get();			
			if(conf.getIdUser()==idClient && conf.getDeleted()==false) {
				Equipment equip=eqr.findById(eq.getIdEquipment()).get();
			eq.setConfigurationName(conf.getName());
			eq.setEquipmentName(equip.getName());
			newList.add(eq);
			}
		}
		return newList;
	}

	public void deleteUserEquipment(UserEquipment userEquipment) {
		uer.delete(userEquipment);
	}

	public List<UserEquipment> addUserEquipment(Integer idUser,String configurationName, List<Integer> equipmentIds) {
		Configuration conf=rpConfig.save(new Configuration(null,configurationName,idUser,false));
		
		List<UserEquipment> list=new ArrayList<UserEquipment>();
		for(Integer id:equipmentIds)
		{
			UserEquipment eq=new UserEquipment(id,conf.getId());
			Equipment equipment=eqr.findById(id).get();
			
			eq.setIdEquipmentType(equipment.getTypeId());
			eq.setConfigurationName(configurationName);
			eq.setEquipmentName(equipment.getName());
			list.add(eq);
			uer.save(eq);
		}
		return list;
	}
	

	public List<UserEquipment> updateUserEquipment(Integer idUser, Integer idConfiguration,String configurationName, List<Integer> equipmentIds) {
		
		Configuration conf=rpConfig.findById(idConfiguration).get();
		conf.setName(configurationName);
		rpConfig.save(conf);
		
		List<UserEquipment> toDelete=uer.findByidConfiguration(idConfiguration);
		
		for(UserEquipment ue:toDelete)
		{
			//for(Integer i:equipmentIds) {
			//Equipment equipment=eqr.findById(i).get();
			
			//if(equipment.getTypeId()==ue.getIdEquipmentType()) {
				 uer.delete(ue);
			//}
			//}
		}
		List<UserEquipment> list=new ArrayList<UserEquipment>();
		for(Integer id:equipmentIds)
		{
			UserEquipment eq=new UserEquipment(id,conf.getId());
			Equipment equipment=eqr.findById(id).get();
			
			eq.setIdEquipmentType(equipment.getTypeId());
			eq.setConfigurationName(configurationName);
			eq.setEquipmentName(equipment.getName());
			list.add(eq);
			uer.save(eq);
		}
		return list;
	}

	public List<UserEquipment> getByIdConfiguration(Integer idConfiguration) {
		List<UserEquipment> list= uer.findByidConfiguration(idConfiguration);
		
		for(UserEquipment eq:list)
		{
			Equipment equipment=eqr.findById(eq.getIdEquipment()).get();
			eq.setEquipmentName(equipment.getName());
			
		}
		return list;
	}
	
	
}

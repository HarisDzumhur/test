package com.example.app.equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.exception.EquipmentException;

@Service
public class EquipmentService {
	private final EquipmentRepository er;
	
	@Autowired
	public EquipmentService(EquipmentRepository er)
	{
		this.er = er;
	}
	
	public Equipment addEquipment(Equipment eq)
	{
		if (eq.getName().isBlank())
			throw new EquipmentException("Name can't be empty!");
		
		List<Equipment> list = er.findByName(eq.getName());
		if (list.isEmpty())
		{
			return er.save(eq);
		}
		else {
			throw new EquipmentException("Equipment with that name already exists!");
		}
	}

	public List<Equipment> getAllEquipment() {
		return er.findAll();
	}
	
	public List<Equipment> getEquipmentById(Integer typeId)
	{
		return er.findBytypeId(typeId);
	}
	public List<Equipment> getListOfUserEquipment(List<Integer> list)
	{
		return er.findAllById(list);
	}
	

	public Equipment update(Equipment equipment) {
		return er.save(equipment);
	}

	public Equipment addEquipment(String name, byte[] image, Integer typeId,
			Boolean isHuntingGear) {
		
		if (name.isBlank())
			throw new EquipmentException("Name can't be empty!");
		
		List<Equipment> list = er.findByName(name);
		if (list.isEmpty())
		{
			Equipment eq=new Equipment(null,name,image,typeId,isHuntingGear);
			return er.save(eq);
		}
		else {
			throw new EquipmentException("Equipment with that name already exists!");
		}
	}
}

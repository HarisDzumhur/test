package com.example.app.equipment.type;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.app.exception.EquipmentTypeException;

@Service
public class EquipmentTypeService {
	private final EquipmentTypeRepository etr;
	
	@Autowired
	public EquipmentTypeService(EquipmentTypeRepository etr)
	{
		this.etr = etr;
	}

	public EquipmentType addEquipmentType(EquipmentType et) {
		List<EquipmentType> list = etr.findByName(et.getName());
		if (!list.isEmpty())
			throw new EquipmentTypeException("That equipment type already exists!");
		else {
			etr.save(et);
			return et;
		}
	}

	public List<EquipmentType> getAllEquipmentTypes() {
		return etr.findAll();
	}
}

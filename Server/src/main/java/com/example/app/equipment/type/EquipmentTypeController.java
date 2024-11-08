package com.example.app.equipment.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/equipment/type")
public class EquipmentTypeController {
	private final EquipmentTypeService ets;
	
	@Autowired
	public EquipmentTypeController(EquipmentTypeService ets)
	{
		this.ets = ets;
	}
	
	@PostMapping("/create")
	public ResponseEntity<EquipmentType> addEquipmentType(@RequestBody EquipmentType et)
	{
		return new ResponseEntity<EquipmentType>(ets.addEquipmentType(et), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<EquipmentType> getAllEquipmentTypes()
	{
		return ets.getAllEquipmentTypes();
	}
}
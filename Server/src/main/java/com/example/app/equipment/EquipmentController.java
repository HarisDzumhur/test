package com.example.app.equipment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/equipment")
public class EquipmentController {
	private final EquipmentService es;
	
	@Autowired
	public EquipmentController(EquipmentService es)
	{
		this.es = es;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Equipment> addEquipment(@RequestBody Equipment eq)
	{
		return new ResponseEntity<Equipment>(es.addEquipment(eq), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Equipment> update(@RequestBody Equipment equipment)
	{
		return new ResponseEntity<Equipment>(es.update(equipment), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<Equipment> getAllEquipment()
	{
		return es.getAllEquipment();
	}
	
	@GetMapping("/byid")
	public List<Equipment> getEquipmentById(@RequestParam Integer typeId)
	{
		return es.getEquipmentById(typeId);
	}
	
	@PutMapping("/list")
	public List<Equipment> getByListOfIds(@RequestBody EquipmentList list)
	{
		return es.getListOfUserEquipment(list.getEquipmentIds());
	}
	
}

package com.example.app.userequipment;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userequipment")
public class UserEquipmentController {
	private final UserEquipmentService ues;
	
	@Autowired
	public UserEquipmentController(UserEquipmentService ues)
	{
		this.ues = ues;
	}
	
	@GetMapping("/all")
	public List<UserEquipment> getAll(@RequestParam Integer idClient)
	{
		return ues.getAll(idClient);
	}
	
	@GetMapping("/configuration/id")
	public List<UserEquipment> getByIdConfiguration(@RequestParam Integer idConfiguration)
	{
		return ues.getByIdConfiguration(idConfiguration);
	}
	
	@PostMapping("/add")
	public List<UserEquipment> addUserEquipment(@RequestBody UserEquipmentAddModel model)
	{
		return ues.addUserEquipment(model.getIdUser(),model.getConfigurationName(),model.getEquipmentIds());
	}
	
	@PutMapping("/update")
	public List<UserEquipment> updateEquipment(@RequestParam Integer idConfiguration,@RequestBody UserEquipmentAddModel model)
	{
		return ues.updateUserEquipment(model.getIdUser(),idConfiguration,model.getConfigurationName(),model.getEquipmentIds());
	}
	
	
//	@DeleteMapping("/delete")
//	public ResponseEntity<String> deleteUserEquipment(@RequestParam Integer idClient, @RequestParam Integer idEquipment)
//	{
//		ues.deleteUserEquipment(new UserEquipment(idClient, idEquipment));
//		return new ResponseEntity<String>("Successfully deleted equipment!", HttpStatus.OK);
//	}
}

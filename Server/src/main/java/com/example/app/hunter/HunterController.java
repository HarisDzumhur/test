package com.example.app.hunter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.client.SimpleClient;

@RestController
@RequestMapping("/hunter")
public class HunterController {

	private final HunterService hs;
	
	@Autowired
	public HunterController(HunterService hs)
	{
		this.hs=hs;
	}
	

	@GetMapping("/section")
	public List<SimpleClient> getByIdSection(@RequestParam Integer idSection)
	{
		return hs.getHuntersFromSection(idSection);
	}
	
	
	@DeleteMapping("/section/remove")
	public ResponseEntity<String> removeHunterFromSection(@RequestParam Integer id)
	{
		hs.removeHunterFromSection(id);
		return new ResponseEntity<String>("Successfully removed hunter!", HttpStatus.OK);
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<Hunter> addUser(@RequestParam Integer idUser)
	{
		return new ResponseEntity<>(hs.addHunter(idUser),HttpStatus.OK);
	}
	
	@PutMapping("/section/add")
	public ResponseEntity<Hunter> addUserToSection(@RequestParam Integer id, @RequestParam Integer idSection)
	{
		return new ResponseEntity<Hunter>(hs.addUserToSection(id, idSection), HttpStatus.OK);
	}
	
	@PutMapping("/section/add/list")
	public ResponseEntity<String> addListOfUsersToSection(@RequestParam Integer idSection,@RequestParam List<Integer> list)
	{
		hs.addListOfUsersToSection(idSection,list);
		return new ResponseEntity<String>("Successfully added users to section!",HttpStatus.OK);
	}
	
	@PutMapping("/group/add")
	public ResponseEntity<Hunter> addUserToGroup(@RequestParam Integer id, @RequestParam Integer idGroup)
	{
		return new ResponseEntity<Hunter>(hs.addHunterToGroup(id, idGroup), HttpStatus.OK);
	}
	
	@DeleteMapping("/group/remove")
	public ResponseEntity<Hunter> removeHunterFromGroup(@RequestParam Integer id)
	{
		return new ResponseEntity<Hunter>(hs.removeHunterFromGroup(id), HttpStatus.OK);
	}
	
}

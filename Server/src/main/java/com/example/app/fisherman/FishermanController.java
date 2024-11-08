package com.example.app.fisherman;

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
@RequestMapping("/fisherman")
public class FishermanController {

	private final FishermanService fs;
	
	@Autowired
	public FishermanController(FishermanService fs)
	{
		this.fs=fs;
	}
	
	
	@GetMapping("/all")
	public List<Fisherman> getAll()
	{
		return fs.getAll();
	}
	
	@GetMapping("/id")
	public ResponseEntity<Fisherman> getById(@RequestParam Integer id)
	{
		return new ResponseEntity<>(fs.getById(id),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Fisherman> addUser(@RequestParam Integer id)
	{
		return new ResponseEntity<>(fs.addUser(id),HttpStatus.OK);
	}
	
	@PutMapping("/group/add")
	public ResponseEntity<Fisherman> addUserToGroup(@RequestParam Integer id, @RequestParam Integer idGroup)
	{
		return new ResponseEntity<Fisherman>(fs.addFishermanToGroup(id, idGroup), HttpStatus.OK);
	}
	
	@GetMapping("/group")
	public List<Fisherman> getByIdGroup(@RequestParam Integer idGroup)
	{
		return fs.getByIdGroup(idGroup);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteFisherman(@RequestParam Integer id)
	{
		fs.deleteFisherman(id);
		return new ResponseEntity<>("Successfully deleted fisherman!",HttpStatus.OK);
	}
	
	@DeleteMapping("/group/remove")
	public ResponseEntity<Fisherman> removeFisherman(@RequestParam Integer id)
	{
		return new ResponseEntity<Fisherman>(fs.removeFisherman(id), HttpStatus.OK);
	}
}

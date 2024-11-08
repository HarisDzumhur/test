package com.example.app.sharing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sharing")
public class LocationSharingController {

	private final LocationSharingService ls;
	
	@Autowired
	public LocationSharingController(LocationSharingService ls)
	{
		this.ls=ls;
	}
	
	
	@GetMapping("/id")
	public ResponseEntity<LocationSharing> getByIds(@RequestParam Integer id1,@RequestParam Integer id2)
	{
		return new ResponseEntity<>(ls.getByIds(id1, id2),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<LocationSharing> add(@RequestBody LocationSharing sharing)
	{
		return new ResponseEntity<>(ls.add(sharing),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> removeByIds(@RequestParam Integer id)
	{
		ls.removeById(id);
		return new ResponseEntity<>("Removed all location sharings for user with given id!",HttpStatus.OK);
	}
	
}

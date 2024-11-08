package com.example.app.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {

	private final LocationService ls;
	
	@Autowired
	public LocationController(LocationService ls)
	{
		this.ls=ls;
	}
	
	@GetMapping("/id")
	public ResponseEntity<Location> getUserLocation(@RequestParam Integer id)
	{
		return new ResponseEntity<Location>(ls.getUserLocation(id),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Location> addUserLocation(@RequestBody Location location)
	{
		return new ResponseEntity<>(ls.addUserLocation(location),HttpStatus.OK);
	}
	
}

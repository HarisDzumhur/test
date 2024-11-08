package com.example.app.animal;

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
@RequestMapping("/admin/animal")
public class AnimalController {

	private final AnimalService as;
	
	@Autowired
	public AnimalController(AnimalService as)
	{
		this.as=as;
	}
	
	@GetMapping("/all")
	public List<Animal> getAll()
	{
		return as.getAll();
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addAnimal(@RequestBody Animal a)
	{
		return new ResponseEntity<String>(as.add(a),HttpStatus.OK);
	}
	
}

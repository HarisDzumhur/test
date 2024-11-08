package com.example.app.configuration;

import java.util.List;

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
@RequestMapping("/configuration")
public class ConfigurationController {

	private final ConfigurationService cs;
	
	@Autowired
	public ConfigurationController(ConfigurationService cs)
	{
		this.cs=cs;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Configuration> add(@RequestBody Configuration configuration)
	{
		return new ResponseEntity<Configuration>(cs.add(configuration),HttpStatus.OK);
	}
	
	@GetMapping
	public List<Configuration> getByIdUser(@RequestParam Integer idUser)
	{
		return cs.getByIdUser(idUser);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteConfiguration(@RequestParam Integer idConfiguration)
	{
		cs.deleteConfiguration(idConfiguration);
		return new ResponseEntity<String>("Successfully deleted configuration!",HttpStatus.OK);
	}
}

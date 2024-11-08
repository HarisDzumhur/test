package com.example.app.marker.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/marker/type")
public class MarkerTypeController {

	private final MarkerTypeService ms;
	
	@Autowired
	public MarkerTypeController(MarkerTypeService ms)
	{
		this.ms=ms;
	}
	
	@GetMapping("/all")
	public List<MarkerType> getAllMarkerTypes()
	{
		return ms.getMarkerTypes();
	}
	
	@PostMapping("/add")
	public ResponseEntity<MarkerType> addNewType(@RequestParam String name)
	{
		return new ResponseEntity<MarkerType>(ms.addMarkerType(new MarkerType(null,name)),HttpStatus.OK);
	}
	
}

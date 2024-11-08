package com.example.app.marker;

import java.util.List;

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
@RequestMapping("/marker")
public class MapMarkerController {

	private final MapMarkerService ms;
	
	public MapMarkerController(MapMarkerService ms)
	{
		this.ms=ms;
	}
	

	
	@GetMapping("/alluser")
	public List<MapMarker> getUserAndPublicMarkers(@RequestParam Integer idUser)
	{
		return ms.getUserAndPublicMarkers(idUser);
	}
	
	@PostMapping("/add")
	public ResponseEntity<MapMarker> createMarker(@RequestBody MapMarker marker)
	{
		return new ResponseEntity<MapMarker>(ms.addMarker(marker),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteMarker(@RequestParam Integer id)
	{
		ms.removeMarker(id);
		return new ResponseEntity<>("Sucessfully deleted marker!",HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<MapMarker> updateMarkerVisibility(@RequestParam Integer id,@RequestParam Boolean isPublic)
	{
		return new ResponseEntity<MapMarker>(ms.updateMarkerVisibility(id, isPublic),HttpStatus.OK);
	}
	
}

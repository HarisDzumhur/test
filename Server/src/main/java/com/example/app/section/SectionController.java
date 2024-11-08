package com.example.app.section;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.client.SimpleClient;

@RestController
@RequestMapping("/section")
public class SectionController {
	private final SectionService ss;
	
	@Autowired
	public SectionController(SectionService ss)
	{
		this.ss = ss;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Section> addNewSection(@RequestParam String name, @RequestParam Integer idGroup, @RequestParam Integer membersCount,
			@RequestParam String description, @RequestPart byte[] image)
	{
		return new ResponseEntity<Section>(ss.saveSection(new Section(null, name, idGroup, membersCount, description, image)), HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<Section> getAllSections(@RequestParam Integer idGroup)
	{
		return ss.getAllSections(idGroup);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Section> getSectionById(@RequestParam Integer id)
	{
		return new ResponseEntity<Section>(ss.getSectionById(id), HttpStatus.OK);
	}
	
	@GetMapping("/my")
	public ResponseEntity<Section> getMySection(@RequestParam Integer id)
	{
		return new ResponseEntity<Section>(ss.getMySection(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteSection(@RequestParam Integer id)
	{
		ss.deleteSection(id);
		return new ResponseEntity<String>("Successfully deleted section!", HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateSection(@RequestParam Integer id, @RequestParam String name, @RequestParam Integer idGroup, @RequestParam Integer membersCount,
			@RequestParam String description, @RequestPart(required=false) byte[] image)
	{
		ss.updateSection(new Section(id, name, idGroup, membersCount, description, image));
		return new ResponseEntity<String>("Successfully updated section!", HttpStatus.OK);
	}
	
	
	@GetMapping("/users/nosection")
	public List<SimpleClient> getNoSectionUsers(@RequestParam Integer idGroup)
	{
		return ss.getNoSectionUsers(idGroup);
	}
	
	
}

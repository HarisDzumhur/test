package com.example.app.taghasforum;

import java.util.List;

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
@RequestMapping("/taghasforum")
public class TagHasForumControlleer {

	private final TagHasForumService ts;

	@Autowired
	public TagHasForumControlleer(TagHasForumService ts) {
		super();
		this.ts = ts;
	}
	
	@GetMapping("/all")
	public List<TagHasForum> getAll()
	{
		return ts.all();
	}
	@PostMapping("/add")
	public ResponseEntity<TagHasForum> add(@RequestBody TagHasForum tf)
	{
		return new ResponseEntity<TagHasForum>(ts.add(tf),HttpStatus.OK);
	}
	
	@GetMapping("/get/idtopic")
	public List<TagHasForum> getByIdTopic(@RequestParam Integer idTopic)
	{
		return ts.getByIdTopic(idTopic);
	}
	
}

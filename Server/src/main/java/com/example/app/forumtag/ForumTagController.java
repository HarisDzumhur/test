package com.example.app.forumtag;

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
@RequestMapping("/tag")
public class ForumTagController {

	private final ForumTagService fs;

	@Autowired
	public ForumTagController(ForumTagService fs) {
		super();
		this.fs = fs;
	}
	@GetMapping("/all")
	public List<ForumTag> getAllTags()
	{
		return fs.getAllTags();
	}
	
	@PostMapping("/add")
	public ResponseEntity<ForumTag> addTag(@RequestBody ForumTag tag)
	{
		return new ResponseEntity<ForumTag>(fs.add(tag),HttpStatus.OK);
	}
	
}

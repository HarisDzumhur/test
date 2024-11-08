package com.example.app.forum;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/forum")
public class ForumController {

	private final ForumService fs;
	
	
	@Autowired
	public ForumController(ForumService fs)
	{
		this.fs=fs;
	}
	
	@GetMapping("/all")
	public List<Forum> all()
	{
		return fs.getAllForums();
	}
	
	@PostMapping("/add")
	public ResponseEntity<Forum> addNewTopic(@RequestParam String title,@RequestParam Integer idClient,@RequestParam LocalDateTime date,
			@RequestParam String description,@RequestPart(required = false) MultipartFile image)
	{
    
	    byte[] imageBytes = null;
	    if (image != null && !image.isEmpty()) {
	        try {
	            imageBytes = image.getBytes();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
		return new ResponseEntity<Forum>(fs.addNewTopic(new Forum(null,title,idClient,imageBytes,date,description)), HttpStatus.OK);
	}
	
	@GetMapping("/my")
	public List<Forum> getMyTopics(@RequestParam Integer idClient)
	{
		return fs.getMyTopics(idClient);
	}
	
}

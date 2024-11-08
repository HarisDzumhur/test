package com.example.app.following;

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
@RequestMapping("/following")
public class FollowingController {
	
	
	private final FollowingService fs;
	
	@Autowired
	public FollowingController(FollowingService fs)
	{
		this.fs=fs;
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody Following follow)
	{
		fs.add(follow);
		return new ResponseEntity<String>("Successfully added a follow!",HttpStatus.OK);
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<String> remove(@RequestBody Following follow)
	{
		fs.remove(follow);
		return new ResponseEntity<String>("Successfully deleted a follow!",HttpStatus.OK);
	}
	
	@GetMapping("/myfollowing")
	public List<Integer> getMyFollowingIds(@RequestParam Integer idUser)
	{
		return fs.getMyFollowingIds(idUser);
	}

}

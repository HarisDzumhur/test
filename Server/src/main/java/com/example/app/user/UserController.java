package com.example.app.user;

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
@RequestMapping("/user")
public class UserController {

	
	private final UserService us;
	
	@Autowired
	public UserController(UserService us)
	{
		this.us=us;
	}
	
	@GetMapping("/all")
	public List<User> getAll()
	{
		return us.getAll();
	}
	
	@GetMapping("/id")
	public ResponseEntity<User> getById(@RequestParam Integer id)
	{
		return new ResponseEntity<User>(us.getById(id),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@RequestParam Integer id)
	{
		return new ResponseEntity<User>(us.addUser(id),HttpStatus.OK);
	}
	
}

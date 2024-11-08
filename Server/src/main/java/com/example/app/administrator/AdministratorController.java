package com.example.app.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdministratorController {
	
	private final AdministratorService as;
	
	@Autowired
	public AdministratorController(AdministratorService as)
	{
		this.as = as;
	}
	
	@GetMapping("/account")
    public ResponseEntity<Administrator> getAccount(@RequestParam String username, @RequestHeader("Password") String password) {
		return new ResponseEntity<>(as.getAccount(username, password), HttpStatus.OK);
    }
	
	@GetMapping("/password")
	public ResponseEntity<String> sendPassword(@RequestParam String email)
	{
		as.sendPassword(email);
		return new ResponseEntity<String>("Password sent to email!", HttpStatus.OK);
	}
	
	@PutMapping("/update/password")
	public ResponseEntity<Administrator> updatePassword(@RequestParam String username, @RequestHeader("Old-Password") String oldPassword,
	        @RequestHeader("New-Password") String newPassword) {
		return new ResponseEntity<>(as.updatePassword(username, oldPassword, newPassword), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Administrator> update(@RequestParam String username, @RequestParam String newName, @RequestParam String newSurname,
			@RequestParam String newEmail)
	{
		return new ResponseEntity<>(as.update(username, newName, newSurname, newEmail), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Administrator> createNewAdministrator(@RequestBody Administrator admin)
	{
		return new ResponseEntity<Administrator>(as.createNewAdministrator(admin), HttpStatus.OK);
	}
}

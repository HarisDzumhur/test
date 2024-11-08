package com.example.app.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.firebase.FirebaseAPI;



@RestController
@RequestMapping("/client")
public class RegisteredClientController {
	
	private final RegisteredClientService rc;
	
	public static ArrayList<CodeToken> FORGOT_PASSWORD_TOKENS=new ArrayList<CodeToken>();
	public static ArrayList<CodeToken> VERIFICATION_TOKENS=new ArrayList<CodeToken>();
	
	@Autowired
	public RegisteredClientController(RegisteredClientService rc)
	{
		this.rc=rc;
	}
	
	@PutMapping("/get/list")
	public List<RegisteredClient> getUsersByList(@RequestBody IntegerList ids)
	{		
		return rc.getClientsByIds(ids.getIntegerList());
	}
	
	@GetMapping("/getbyid")
	public ResponseEntity<RegisteredClient> getUserById(@RequestParam Integer id)
	{
		return new ResponseEntity<RegisteredClient>(rc.getUserById(id),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<RegisteredClient> all()
	{
		return rc.getRegisteredClients();
	}
	
	@GetMapping("/exists")
	public Boolean checkMailExists(@RequestParam String mail)
	{
		return rc.checkMailExists(mail);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<String> delete(@RequestParam Integer id)
	{
		rc.delete(id);
		return new ResponseEntity<String>("Successfully deleted account!", HttpStatus.OK);
	}
	
	
	
	@PutMapping("/update/password")
	public ResponseEntity<RegisteredClient> updatePassword(@RequestParam Integer id, @RequestParam String oldPassword, @RequestParam String newPassword) {
		return new ResponseEntity<>(rc.updatePassword(id, oldPassword, newPassword), HttpStatus.OK);
	}
	
	
	@PutMapping("/update/name")
	public ResponseEntity<RegisteredClient> updateName(@RequestParam Integer id, @RequestParam String newName) {
		return new ResponseEntity<>(rc.updateName(id, newName), HttpStatus.OK);
	}
	
	@PutMapping("/update/surname")
	public ResponseEntity<RegisteredClient> updateSurname(@RequestParam Integer id, @RequestParam String newSurname) {
		return new ResponseEntity<>(rc.updateSurname(id, newSurname), HttpStatus.OK);
	}
	
	@PutMapping("/update/image")
	public ResponseEntity<RegisteredClient> updateImage(@RequestParam Integer id, @RequestPart(required=false) byte[] newImage) {
		return new ResponseEntity<>(rc.updateImage(id, newImage), HttpStatus.OK);
	}
	
	@GetMapping("/codeforgotten")
	public ResponseEntity<String> getCodeForgottenPassword(@RequestParam String email)
	{
		Random r=new Random();
		Integer num=r.nextInt(r.nextInt(19999)+80000);
		
		rc.sendToken(email, num.toString(),FORGOT_PASSWORD_TOKENS,false);
		
		
		return new ResponseEntity<String>("Code sent!",HttpStatus.OK);
	}
	@GetMapping("/codeverify")
	public ResponseEntity<String> getCodeVerifyAccount(@RequestParam String email)
	{
		Random r=new Random();
		Integer num=r.nextInt(r.nextInt(19999)+80000);
		

		rc.sendToken(email, num.toString(),VERIFICATION_TOKENS,true);
		
		
		return new ResponseEntity<String>("Code sent!",HttpStatus.OK);
	}
	
	@GetMapping("/validateforgotten")
	public ResponseEntity<String> validateCodeForgottenPassowrd(@RequestParam String email, @RequestParam String token)
	{
		rc.validateForgottenPassword(email, token,FORGOT_PASSWORD_TOKENS,false);
		return new ResponseEntity<>("Successfully validated code!",HttpStatus.OK);
	}
	@GetMapping("/validateverify")
	public ResponseEntity<String> validateCodeVerifyAccount(@RequestParam String email, @RequestParam String token)
	{
		rc.validateForgottenPassword(email, token,VERIFICATION_TOKENS,true);
		return new ResponseEntity<>("Successfully validated code!",HttpStatus.OK); 
	}
	@PostMapping("/update/newpassword")
	public ResponseEntity<String> changePassword(@RequestParam String mail,@RequestParam String newPassword)
	{
		rc.changePassword(mail,newPassword);
		return new ResponseEntity<String>("Successfully updated password!",HttpStatus.OK);
	}
	
	@PutMapping("/block")
	public ResponseEntity<String> blockUser(@RequestParam Integer idClient)
	{
		rc.blockUser(idClient);
		return new ResponseEntity<>("Successfully blocked the user!",HttpStatus.OK);
	}
	
	@PutMapping("/unblock")
	public ResponseEntity<String> unblockUser(@RequestParam Integer idClient)
	{
		rc.unblockUser(idClient);
		return new ResponseEntity<>("Successfully unblocked the user!",HttpStatus.OK);
	}
	
	@GetMapping("/credentials/valid")
	public ResponseEntity<Boolean> checkCredentialsAreValid(@RequestParam String mail,@RequestParam String password)
	{
		return new ResponseEntity<Boolean>(rc.checkCredentialsValid(mail, password),HttpStatus.OK);
	}
}

package com.example.app.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationController {

	private final AuthenticationService authService;

	public AuthenticationController(AuthenticationService authService) {

		this.authService = authService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestParam(required=false) String fcmToken,@RequestParam String password,
			@RequestParam String mail,@RequestParam String name,@RequestParam String surname,@RequestPart(required=false) byte[] image)
	{
		RegisteredClient request=new RegisteredClient(null,password,mail,name,surname,image,false,false,false,fcmToken,false);		
		return new ResponseEntity<AuthenticationResponse>(authService.register(request),HttpStatus.OK);
	}
	
	@PostMapping("/register/group")
	public ResponseEntity<AuthenticationResponse> registerGroup(@RequestBody ClientGroup group)
	{
		return new ResponseEntity<AuthenticationResponse>(authService.registerGroup(group),HttpStatus.OK);
	}
	

	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody RegisteredClient request)
	{
		return new ResponseEntity<AuthenticationResponse>(authService.authenticate(request),HttpStatus.OK);
	}
	
	@PutMapping("/clientlogout")
	public ResponseEntity<String> logout(@RequestParam String token,@RequestParam String fcmToken)
	{
		return new ResponseEntity<String>(authService.logout(token,fcmToken),HttpStatus.OK);
	}
	
	@PostMapping("/login/token")
	public ResponseEntity<AuthenticationResponse> loginWithToken(@RequestParam String token)
	{
		return new ResponseEntity<AuthenticationResponse>(authService.authenticateWithToken(token),HttpStatus.OK);
	}
	
	@GetMapping("/getuser")
	public ResponseEntity<RegisteredClient> getUserWithToken(@RequestParam String token)
	{
		return new ResponseEntity<RegisteredClient>(authService.getUserFromToken(token),HttpStatus.OK);
	}
	
	@GetMapping("/get/account/type")
	public ResponseEntity<String> getAccountType(@RequestParam String token)
	{
		return new ResponseEntity<String>(authService.getAccountType(token),HttpStatus.OK);
	}
	
	@GetMapping("/getgroup")
	public ResponseEntity<ClientGroup> getGroupWithToken(@RequestParam String token)
	{
		return new ResponseEntity<ClientGroup>(authService.getGroupFromToken(token),HttpStatus.OK);
	}
	
	
//	@PostMapping("/login/google1")
//    public ResponseEntity<AuthenticationResponse> googleSignIn(@RequestBody String token) {
//      
//		return new ResponseEntity<AuthenticationResponse>(authService.googleSignIn(token),HttpStatus.OK);
//    }
//	
//	@PostMapping("/register/google1")
//	public ResponseEntity<AuthenticationResponse> googleRegister(@RequestBody String token)
//	{
//		return new ResponseEntity<AuthenticationResponse>(authService.googleRegister(token),HttpStatus.OK);
//	}
	
	
	@PostMapping("/login/google")
    public ResponseEntity<AuthenticationResponse> googleSignInMail(@RequestParam(required=false) String fcmToken,@RequestBody String mail) {
     
		return new ResponseEntity<AuthenticationResponse>(authService.googleSignInMail(mail,fcmToken),HttpStatus.OK);
    }
	
	@PostMapping("/register/google")
	public ResponseEntity<AuthenticationResponse> googleRegisterMail(@RequestParam(required=false) String fcmToken,@RequestBody RegisterGoogleInformation rgi)
	{
		return new ResponseEntity<AuthenticationResponse>(authService.googleRegister(rgi.getName(),rgi.getSurname(),rgi.getMail(),rgi.getPicture(),fcmToken),HttpStatus.OK);
	}
	
}

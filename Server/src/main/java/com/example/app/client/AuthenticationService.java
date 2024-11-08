package com.example.app.client;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;

import javax.imageio.ImageIO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.app.exception.AccessDeniedException;
import com.example.app.exception.AccountBlockedException;
import com.example.app.exception.AccountDeletedException;
import com.example.app.exception.GoogleAccountException;
import com.example.app.exception.UserAlreadyExistsException;
import com.example.app.exception.UserNotFoundException;
import com.example.app.firebase.FirebaseAPI;
import com.example.app.fisherman.Fisherman;
import com.example.app.fisherman.FishermanRepository;
import com.example.app.following.FollowingRepository;
import com.example.app.group.Group;
import com.example.app.group.GroupRepository;
import com.example.app.group.fish.FishingGroup;
import com.example.app.group.fish.FishingGroupRepository;
import com.example.app.group.hunt.HuntingGroup;
import com.example.app.group.hunt.HuntingGroupRepository;
import com.example.app.hunter.Hunter;
import com.example.app.hunter.HunterRepository;
import com.example.app.user.User;
import com.example.app.user.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import jakarta.persistence.Column;

@Service
public class AuthenticationService {

	private final RegisteredClientRepository rp;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final HunterRepository rpHunter;
	private final UserRepository rpUser;
	private final FishermanRepository rpFisherman;
	private final GroupRepository rpGroup;
	private final FishingGroupRepository rpGFishing;
	private final HuntingGroupRepository rpGHunting;
	private final FollowingRepository rpFollowing;
	public AuthenticationService(RegisteredClientRepository rp, PasswordEncoder passwordEncoder,
			JwtService jwtService,AuthenticationManager authenticationManager,HunterRepository rpHunter,UserRepository rpUser,FishermanRepository rpFisherman,
			GroupRepository rpGroup,
	 FishingGroupRepository rpGFishing,
	HuntingGroupRepository rpGHunting,
	FollowingRepository rpFollowing) {
		
		this.rp = rp;
		this.passwordEncoder = passwordEncoder;
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
		this.rpHunter=rpHunter;
		this.rpUser=rpUser;
		this.rpFisherman=rpFisherman;
		this.rpGroup=rpGroup;
		this.rpGFishing=rpGFishing;
		this.rpGHunting=rpGHunting;
		this.rpFollowing=rpFollowing;
	}
	public AuthenticationResponse register(RegisteredClient request)
	{
		String fcmToken=request.getFcmToken();
		if(request.getPassword()!=null)
		request.setPassword(passwordEncoder.encode(request.getPassword()));
		RegisteredClient client=null;
		try {
		client=rp.findBymail(request.getMail()).get();
		}catch(Exception ex) {}
		
		if(client!=null)
			throw new UserAlreadyExistsException("User with that email already exists!");
		
		request=rp.save(request);
		
		rpUser.save(new User(request.getId()));
		rpHunter.save(new Hunter(request.getId(),null,null));
		rpFisherman.save(new Fisherman(request.getId(), null));
		
		String token=jwtService.generateToken(request);
		FirebaseAPI.addMapping(request.getId(), fcmToken);
		
		return new AuthenticationResponse(token);
	}
	
	public AuthenticationResponse registerGroup(RegisteredClient request, String type, Group group) {
		
		String fcmToken=request.getFcmToken();
		if(request.getPassword()!=null)
			request.setPassword(passwordEncoder.encode(request.getPassword()));
			RegisteredClient client=null;
			try {
			client=rp.findBymail(request.getMail()).get();
			}catch(Exception ex) {}
			
			if(client!=null)
				throw new UserAlreadyExistsException("User with that email already exists!");
			
			request=rp.save(request);
			
			group.setId(request.getId());
			rpGroup.save(group);
			
			if(type.equals("hunting"))
			{
				rpGHunting.save(new HuntingGroup(request.getId()));
			}
			else if(type.equals("fishing"))
			{
				rpGFishing.save(new FishingGroup(request.getId()));
			}
		
		 String jwtToken = jwtService.generateToken(request);
		 FirebaseAPI.addMapping(request.getId(), fcmToken);
		return new AuthenticationResponse(jwtToken);
	}
	
	public AuthenticationResponse registerGroup(ClientGroup group) {
		RegisteredClient request=new RegisteredClient(null,group.getPassword(),group.getMail(),
				group.getName(),group.getSurname(),group.getImage(),false,false,false,null,true);
		
		if(request.getPassword()!=null)
			request.setPassword(passwordEncoder.encode(request.getPassword()));
			RegisteredClient client=null;
			try {
			client=rp.findBymail(request.getMail()).get();
			}catch(Exception ex) {}
			
			if(client!=null)
				throw new UserAlreadyExistsException("User with that email already exists!");
			
			
			request=rp.save(request);
			
			
			Group g=new Group(request.getId(),group.getRegistrationNumber(),group.getGroupName(),group.getDescription(),group.getLocation(),
					group.getContact(),group.getMembersCount(),group.getDate());
			
			rpGroup.save(g);
			
			if(group.getIsFishingGroup()==false)
			{
				rpGHunting.save(new HuntingGroup(request.getId()));
			}
			else
			{
				rpGFishing.save(new FishingGroup(request.getId()));
			}
			
		
		String jwtToken = jwtService.generateToken(request);
		FirebaseAPI.addMapping(request.getId(), null);
		return new AuthenticationResponse(jwtToken);
	}

	
	
	
	public AuthenticationResponse authenticate(RegisteredClient request)
	{
		String fcmToken=request.getFcmToken();
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));
		RegisteredClient user=rp.findBymail(request.getMail()).orElseThrow(()->new AccessDeniedException("Credentials are not valid!"));
		if(user.getGoogle_signin()==true)
			throw new UserNotFoundException("Can't sign in a google sign-in user over non-google login request!");
		
		if(user.getDeleted()==true)
		{
			throw new AccountDeletedException("Account has been deleted!");
		}
		if(user.getBlocked()==true)
		{
			throw new AccountBlockedException("Account is blocked!");
		}
		
		String token=jwtService.generateToken(user);
		FirebaseAPI.addMapping(user.getId(), fcmToken);
		
		return new AuthenticationResponse(token);
	}
	
	
	public AuthenticationResponse authenticateWithToken(String token)
	{
		String mail=jwtService.extractEmail(token);
		
		RegisteredClient client=rp.findBymail(mail).orElseThrow(()->new AccessDeniedException("Credentials are not valid!"));
		if(client.getDeleted()==true)
		{
			throw new AccountDeletedException("Account has been deleted!");
		}
		if(client.getBlocked()==true)
		{
			throw new AccountBlockedException("Account is blocked!");
		}
		
		return new AuthenticationResponse(token);
	}
	public RegisteredClient getUserFromToken(String token) {
		String mail=jwtService.extractEmail(token);

		
		RegisteredClient client=rp.findBymail(mail).get();
		
		return client;
	}
	
	
	 public AuthenticationResponse googleSignInMail(String mail,String fcmToken) {
	       
	        RegisteredClient user = rp.findBymail(mail).orElseThrow(()->new AccessDeniedException("Credentials are not valid!"));
	        if(user.getGoogle_signin()==false)
	        	throw new GoogleAccountException("Account is not a google account");
	        
	        if(user.getDeleted()==true)
			{
				throw new AccountDeletedException("Account has been deleted!");
			}
			if(user.getBlocked()==true)
			{
				throw new AccountBlockedException("Account is blocked!");
			}

			
	        String jwtToken = jwtService.generateToken(user);
	        FirebaseAPI.addMapping(user.getId(), fcmToken);
	        return new AuthenticationResponse(jwtToken);
	    }
	
//	 public AuthenticationResponse googleSignIn(String request) {
//	        GoogleIdToken idToken = verifyGoogleIdToken(request);
//	        if (idToken == null) {
//	            throw new UserNotFoundException("Invalid google token");
//	        }
//
//	        GoogleIdToken.Payload payload = idToken.getPayload();
//	        
//
//	        RegisteredClient user = rp.findBymail(payload.getEmail()).get();
//
//	        String jwtToken = jwtService.generateToken(user);
//
//	        return new AuthenticationResponse(jwtToken);
//	    }
		
		private GoogleIdToken verifyGoogleIdToken(String idTokenString) {
	        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory()).
	        		setAudience(Collections.singletonList("252064808782-rjlrncd09ss87f03p9vb42bkco95nnve.apps.googleusercontent.com")).build();
	        	
	        try {
	            return verifier.verify(idTokenString);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return null;
	    }
		
		
//		public AuthenticationResponse googleRegister(String token) {
//			
//			GoogleIdToken idToken = verifyGoogleIdToken(token);
//	        if (idToken == null) {
//	            throw new UserNotFoundException("Invalid google token");
//	        }
//	        	
//	        GoogleIdToken.Payload payload = idToken.getPayload();
//	        RegisteredClient client=null;
//			try {
//			client=rp.findBymail(payload.getEmail()).get();
//			}catch(Exception ex) {}
//			
//			if(client!=null)
//				throw new UserAlreadyExistsException("User with that email already exists!");
//			
//	        
//            String name = (String) payload.get("name");
//            String pictureUrl = (String) payload.get("picture");
//            
//            byte[] imageBytes = downloadImageAsByteArray(pictureUrl);
//            
//            String split[]=name.split(" ");
//            client=new RegisteredClient(null,payload.getEmail(),payload.getEmail(),split[0],split[1],imageBytes,false,false,true);
//            client.setPassword(passwordEncoder.encode(client.getPassword()));
//			client=rp.save(client);
//			rpUser.save(new User(client.getId()));
//			rpHunter.save(new Hunter(client.getId(),null));
//			rpFisherman.save(new Fisherman(client.getId(), null));
//			
//			 String jwtToken = jwtService.generateToken(client);
//			 
//			 return new AuthenticationResponse(jwtToken);
//		}
		
		public AuthenticationResponse googleRegister(String name,String surname,String mail,String pictureUrl,String fcmToken)
		{
			 RegisteredClient client=null;
				try {
				client=rp.findBymail(mail).get();
				}catch(Exception ex) {}
				
				if(client!=null)
					throw new UserAlreadyExistsException("User with that email already exists!");
				
			byte[] imageBytes = downloadImageAsByteArray(pictureUrl);
			client=new RegisteredClient(null,mail,mail,name,surname,imageBytes,false,false,true,fcmToken,true);
			client.setPassword(passwordEncoder.encode(client.getPassword()));
			client=rp.save(client);
			rpUser.save(new User(client.getId()));
			rpHunter.save(new Hunter(client.getId(),null,null));
			rpFisherman.save(new Fisherman(client.getId(), null));
			
			 String jwtToken = jwtService.generateToken(client);
			 FirebaseAPI.addMapping(client.getId(), fcmToken);
			 return new AuthenticationResponse(jwtToken);
		}
		private byte[] downloadImageAsByteArray(String pictureUrl) {
			try {
			URL url=new URL(pictureUrl);
			
			BufferedImage image=ImageIO.read(url);
			image=removeAlphaChannel(image);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			
			byte[] byteArray = baos.toByteArray();
			return byteArray;
			}catch(Exception ex)
			{
				//ex.printStackTrace();
			}
			return null;
		}
	
		private static BufferedImage removeAlphaChannel(BufferedImage img) {
		    if (!img.getColorModel().hasAlpha()) {
		        return img; 
		    }
		    BufferedImage target = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		    Graphics2D g = target.createGraphics();
		    g.drawImage(img, 0, 0, null); 
		    g.dispose();
		    return target;
		}
		public String logout(String token, String fcmToken) {
			
			 RegisteredClient client=getUserFromToken(token);
			 
			 FirebaseAPI.removeMapping(client.getId(), fcmToken);
			 
			return "Sucessfully logged out!";
		}
		public String getAccountType(String token) {
			RegisteredClient client=getUserFromToken(token);
			
			if(rpGFishing.findById(client.getId()).isPresent())
				return "Fishing";
			else if(rpGHunting.findById(client.getId()).isPresent())
				return "Hunting";
			
			return "Client";
		}
		public ClientGroup getGroupFromToken(String token) {
			String mail=jwtService.extractEmail(token);
			RegisteredClient client=rp.findBymail(mail).get();
			Group group=rpGroup.findById(client.getId()).orElseThrow(()->new UserNotFoundException("Group with that id doesn't exist!"));
			Boolean isFishingGroup=false;
			if(rpGFishing.findById(client.getId()).isPresent())
				isFishingGroup=true;
			
			Integer followCount=rpFollowing.findByidGroup(group.getId()).size();
			
			return new ClientGroup(client.getId(),client.getPassword(),client.getMail(),client.getName(),client.getSurname(),client.getImage(),
					client.getBlocked(),client.getDeleted(),client.getGoogle_signin(),group.getRegistrationNumber(),group.getName(),group.getDescription(),
					group.getLocation(),group.getContact(),group.getMembersCount(),group.getDate(),isFishingGroup,followCount);
		}
		
	
	
}

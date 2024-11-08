package com.example.app.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.exception.GoogleAccountException;
import com.example.app.exception.IncorrectPasswordException;
import com.example.app.exception.InvalidTokenException;
import com.example.app.exception.MailException;
import com.example.app.exception.UserAlreadyExistsException;
import com.example.app.exception.UserNotFoundException;
import com.example.app.exception.UserUpdateException;
import com.example.app.fisherman.Fisherman;
import com.example.app.fisherman.FishermanRepository;
import com.example.app.following.FollowingRepository;
import com.example.app.group.Group;
import com.example.app.group.GroupRepository;
import com.example.app.group.fish.FishingGroupRepository;
import com.example.app.group.request.GroupRequestRepository;
import com.example.app.hunter.Hunter;
import com.example.app.hunter.HunterRepository;
import com.example.app.mail.MailAPI;
import com.example.app.notification.NotificationRepository;
import com.example.app.reply.ForumReplyRepository;
import com.example.app.section.Section;
import com.example.app.section.SectionRepository;

import jakarta.transaction.Transactional;

@Service
public class RegisteredClientService {

	private final RegisteredClientRepository rc;
	private final FishermanRepository rpFisherman;
	private final HunterRepository rpHunter;
	private final SectionRepository rpSection;
	private final GroupRepository rpGroup;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final FollowingRepository rpFollowing;
	private final NotificationRepository rpNotification;
	private final ForumReplyRepository rpReply;
	private final GroupRequestRepository rpRequest;
	
	private static int TOKEN_PERIOD_HOURS;
	private static String USERNAME,PASSWORD;
	
	static {
		try(InputStream input=RegisteredClientService.class.getClassLoader().getResourceAsStream("mail.properties")){
		ResourceBundle bundle=new PropertyResourceBundle(input);
		TOKEN_PERIOD_HOURS=Integer.parseInt(bundle.getString("TOKEN_PERIOD_HOURS"));
		USERNAME = bundle.getString("username");
		PASSWORD = bundle.getString("password");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	@Autowired
	public RegisteredClientService(RegisteredClientRepository rc, FishermanRepository rpFisherman,
			HunterRepository rpHunter, SectionRepository rpSection, GroupRepository rpGroup,PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager,
			FollowingRepository rpFollowing,NotificationRepository rpNotification,ForumReplyRepository rpReply,GroupRequestRepository rpRequest) {
		super();
		this.rc = rc;
		this.rpFisherman = rpFisherman;
		this.rpHunter = rpHunter;
		this.rpSection = rpSection;
		this.rpGroup = rpGroup;
		this.passwordEncoder=passwordEncoder;
		this.authenticationManager=authenticationManager;
		this.rpFollowing=rpFollowing;
		this.rpNotification=rpNotification;
		this.rpReply=rpReply;
		this.rpRequest=rpRequest;
	}
	
	public List<RegisteredClient> getClientsByIds(List<Integer> ids)
	{
		List<RegisteredClient> clients=new ArrayList<RegisteredClient>();
		
		for(Integer i:ids)
		{
			RegisteredClient client=getUserById(i);
			client.setImage(null);
			clients.add(client);
		}
		return clients;
		
	}
	
	
	public List<RegisteredClient> getRegisteredClients()
	{
		List<RegisteredClient> clients=rc.findAll();
		List<RegisteredClient> copy=new ArrayList<RegisteredClient>();
		
		for(RegisteredClient client:clients)
		{
			if(client.getDeleted()==false)
				copy.add(client);
		}
		
		return copy;
	}
	
	
	


	public RegisteredClient addAccount(RegisteredClient client)
	{
		RegisteredClient list=rc.findBymail(client.getMail()).get();
		if(list!=null)
		{
			return rc.save(client);
		}
		throw new UserAlreadyExistsException("User with that email already exists!");
	}
	public RegisteredClient changePassword(String mail,String newPassword)
	{
		if(newPassword.isBlank())
			throw new UserUpdateException("New password is blank!");
		
		Optional<RegisteredClient> oc = rc.findBymail(mail);
		if (oc.isPresent()) {
			RegisteredClient client = oc.get();
			if(client.getGoogle_signin()==true)
				throw new GoogleAccountException("Can't change password to a user that is registered with Google account!");
			
            	client.setPassword(passwordEncoder.encode(newPassword));
                rc.save(client);
                return client;
            }
         else {
            throw new UserNotFoundException("User with the given mail does not exist!");
        }
	}
	public RegisteredClient updatePassword(Integer id, String oldPassword, String newPassword)
	{
		if(oldPassword.equals(newPassword))
			throw new IncorrectPasswordException("Old and new password are the same! Must be different!");
		if(newPassword.isBlank())
			throw new UserUpdateException("New password is blank!");
		
		Optional<RegisteredClient> oc = rc.findById(id);
		if (oc.isPresent()) {
			RegisteredClient client = oc.get();
			if(client.getGoogle_signin()==true)
				throw new GoogleAccountException("Can't change password to a user that is registered with Google account!");
            if (checkCredentialsValid(client.getMail(),oldPassword)) {
            	client.setPassword(passwordEncoder.encode(newPassword));
                rc.save(client);
                return client;
            } else {
                throw new IncorrectPasswordException("Old password incorrect!");
            }
        } else {
            throw new UserNotFoundException("User with the given id does not exist!");
        }
	}
	
	

	public RegisteredClient updateName(Integer id, String newName) {
		if(newName.isBlank())
			throw new UserUpdateException("New name is blank!");
		
		Optional<RegisteredClient> oc = rc.findById(id);
		if (oc.isPresent()) {
			RegisteredClient client = oc.get();
            client.setName(newName);
            rc.save(client);
            return client;
        } else {
            throw new UserNotFoundException("User with the given id does not exist!");
        }
	}

	public RegisteredClient updateSurname(Integer id, String newSurname) {
		if(newSurname.isBlank())
			throw new UserUpdateException("New surname is blank!");
		
		Optional<RegisteredClient> oc = rc.findById(id);
		if (oc.isPresent()) {
			RegisteredClient client = oc.get();
            client.setSurname(newSurname);
            rc.save(client);
            return client;
        } else {
            throw new UserNotFoundException("User with the given id does not exist!");
        }
	}

	public RegisteredClient updateImage(Integer id, byte[] newImage) {
		
		Optional<RegisteredClient> oc = rc.findById(id);
		if (oc.isPresent()) {
			RegisteredClient client = oc.get();
            client.setImage(newImage);
            rc.save(client);
            return client;
        } else {
            throw new UserNotFoundException("User with the given id does not exist!");
        }
	}
	
	private static Properties loadMailConfig() throws FileNotFoundException, IOException {
		Properties mailProp = new Properties();
		mailProp.load(RegisteredClientService.class.getClassLoader().getResourceAsStream("mail.properties"));
			
		return mailProp;
	}
	public RegisteredClient sendToken(String email,String token,ArrayList<CodeToken> TOKENS,Boolean isVerificationToken)
	{
		RegisteredClient client=rc.findBymail(email).get();
		if (client!=null) {
			if(client.getGoogle_signin()==true)
				throw new GoogleAccountException("Can't send code to user that is registered with Google account!");
		CodeToken toRemove=null;
		for(CodeToken t:TOKENS)
		{
			if(t.getEmail().equals(email))
			{
				toRemove=t;
				break;
			}
		}
		if(toRemove!=null)
			TOKENS.remove(toRemove);
		TOKENS.add(new CodeToken(email,LocalDateTime.now(),token));
		 
		String title=isVerificationToken==true?"Verification token":"Forgotten password token";
		
		    MailAPI.sendMail(client.getMail(), title, "Your authentication token is: "+token);
		
		 return client;
		}
		else {
			throw new UserNotFoundException("User with the given username does not exist!"); 
		}
	}
	
	public RegisteredClient validateForgottenPassword(String email,String token,ArrayList<CodeToken> TOKENS,Boolean isVerificationToken)
	{
		boolean found=false;
		RegisteredClient client=rc.findBymail(email).get();
		if (client!=null) {
		LocalDateTime tokenDate=null;
		CodeToken toRemove=null;
		for(CodeToken t:TOKENS)
		{
			if(t.getEmail().equals(email) && t.getToken().equals(token))
			{
				found=true;
				tokenDate=t.getDate();
				toRemove=t;
				break;
			}
		}
		if(found==false)
		{
			throw new InvalidTokenException("Token is invalid!");
		}
		else
		{
			if(tokenDate.isAfter(LocalDateTime.now().minusHours(TOKEN_PERIOD_HOURS))) {
            TOKENS.remove(toRemove);
            if(isVerificationToken==true) {
            	client.setIsVerified(true);
            	rc.save(client);
            }
            return client;
			}
			else {
				throw new InvalidTokenException("Token is not valid anymore!");
			}
		}
		}
		else {
			throw new UserNotFoundException("User with the given username does not exist!");
		}
		
	}
	
	
	
	
	public void blockUser(Integer idClient)
	{
		RegisteredClient client = rc.findById(idClient).get();
		if (client.getDeleted() == true)
			throw new UserNotFoundException("User has been deleted!");
		client.setBlocked(true);
		rc.save(client);
	}
	public void unblockUser(Integer idClient)
	{
		RegisteredClient client = rc.findById(idClient).get();
		if (client.getDeleted() == true)
			throw new UserNotFoundException("User has been deleted!");
		client.setBlocked(false);
		rc.save(client);
	}
	@Transactional
	public void delete(Integer id) {
		RegisteredClient client = rc.findById(id).get();
		client.setDeleted(true);
	
		client.setMail((client.getMail()+System.currentTimeMillis()).hashCode()+"");
		Hunter hunter=null;
		Fisherman fisherman=null;
		try {
		hunter=rpHunter.findById(client.getId()).get();
		fisherman=rpFisherman.findById(client.getId()).get();
		}catch(Exception ex) {}
		if(hunter!=null && hunter.getIdSection()!=null)
		{
			Section s=rpSection.findById(hunter.getIdSection()).get();
			s.setMembersCount(s.getMembersCount()-1);
			Group group=rpGroup.findById(s.getIdGroup()).get();
			group.setMembersCount(group.getMembersCount()-1);
			
			rpGroup.save(group);
			rpSection.save(s);
			hunter.setIdGroup(null);
			hunter.setIdSection(null);
			rpHunter.save(hunter);
		}
		if(fisherman!=null && fisherman.getIdGroup()!=null)
		{
			Group group=rpGroup.findById(fisherman.getIdGroup()).get();
			group.setMembersCount(group.getMembersCount()-1);
			rpGroup.save(group);
			fisherman.setIdGroup(null);
			rpFisherman.save(fisherman);
		}
		rpReply.deleteByIdResponseUser(id);
		rpNotification.deleteByIdClient(id);
		rpFollowing.deleteByidUser(id);
		rpRequest.deleteByIdUser(id);
		
		rc.save(client);
	}

	public Boolean checkMailExists(String mail) {
		try {
			rc.findBymail(mail).get();
			return true;
		}
		catch(Exception ex) {
		return false;
		}
	}

	public RegisteredClient getUserById(Integer id) {
		RegisteredClient client=rc.findById(id).orElseThrow(()->new UserNotFoundException("User with that id doesn't exist"));
		return client;
	}
	
	public Boolean checkCredentialsValid(String mail,String password)
	{
		try {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mail, password));
		return true;
		}catch(Exception ex)
		{
			return false;
		}
	}
	
	
	
}

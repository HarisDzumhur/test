package com.example.app.group;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.client.ClientGroup;
import com.example.app.client.RegisteredClient;
import com.example.app.client.RegisteredClientRepository;
import com.example.app.client.SimpleClient;
import com.example.app.exception.GroupException;
import com.example.app.exception.UserAlreadyInGroupException;
import com.example.app.fisherman.Fisherman;
import com.example.app.fisherman.FishermanRepository;
import com.example.app.following.FollowingId;
import com.example.app.following.FollowingRepository;
import com.example.app.group.fish.FishingGroupRepository;
import com.example.app.group.request.GroupRequestRepository;
import com.example.app.hunter.Hunter;
import com.example.app.hunter.HunterRepository;
import com.example.app.notification.NotificationMessage;
import com.example.app.notification.NotificationService;
import com.example.app.section.Section;
import com.example.app.section.SectionRepository;

@Service
public class GroupService {
	private final GroupRepository gr;
	private final RegisteredClientRepository rpClient;
	private final FishingGroupRepository rpGFishing;
	private final FishermanRepository rpFisherman;
	private final HunterRepository rpHunter;
	private final GroupRequestRepository rpRequest;
	private final SectionRepository rpSection;
	private final FollowingRepository rpFollowing;
	private final NotificationService notificationService;
	@Autowired
	public GroupService(GroupRepository gr,RegisteredClientRepository rpClient,FishingGroupRepository rpGFishing,FishermanRepository rpFisherman,
			HunterRepository rpHunter,GroupRequestRepository rpRequest,SectionRepository rpSection,FollowingRepository rpFollowing,NotificationService notificationService)
	{
		this.gr = gr;
		this.rpClient=rpClient;
		this.rpGFishing=rpGFishing;
		this.rpFisherman=rpFisherman;
		this.rpHunter=rpHunter;
		this.rpRequest=rpRequest;
		this.rpSection=rpSection;
		this.rpFollowing=rpFollowing;
		this.notificationService=notificationService;
	}

	public Group saveGroup(Group group) {
		if (group.getMembersCount() == null || group.getMembersCount() < 0)
			throw new GroupException("Members field is invalid!");
		if (group.getRegistrationNumber() == null)
			throw new GroupException("Registration number is empty!");
		if (group.getContact().isBlank())
			throw new GroupException("Contact field is empty!");
		if (group.getDescription().isBlank())
			throw new GroupException("Description field is empty!");
		if (group.getLocation().isBlank())
			throw new GroupException("Location field is empty!");
		if (group.getName().isBlank())
			throw new GroupException("Name field is empty!");
		if (group.getDate() == null)
			throw new GroupException("Date field is empty!");
		
		gr.save(group);
		return group;
	}

	public void deleteGroup(Integer id) {
		gr.deleteById(id);
	}

	public List<ClientGroup> getAllGroups() {
		
		List<Group> groups=gr.findAll();
		List<ClientGroup> returner=new ArrayList<ClientGroup>();
		for(Group group:groups)
		{
			RegisteredClient client=rpClient.findById(group.getId()).get();
			Boolean isFishingGroup=false;
			if(rpGFishing.findById(client.getId()).isPresent())
				isFishingGroup=true;
			if(client.getDeleted()==false) {
				Integer followCount=rpFollowing.findByidGroup(group.getId()).size();
			returner.add(new ClientGroup(client.getId(),client.getPassword(),client.getMail(),client.getName(),client.getSurname(),client.getImage(),
					client.getBlocked(),client.getDeleted(),client.getGoogle_signin(),group.getRegistrationNumber(),group.getName(),group.getDescription(),
					group.getLocation(),group.getContact(),group.getMembersCount(),group.getDate(),isFishingGroup,followCount));
			}
		}
		return returner;
	}

	public ClientGroup getMyGroup(Integer idUser) {
		
		RegisteredClient client=rpClient.findById(idUser).get();
		Boolean isFishingGroup=false;
		Integer idFishing=rpFisherman.findById(idUser).get().getIdGroup();
		Group group=null;
		if(idFishing!=null)
		{
			isFishingGroup=true;
			 group=gr.findById(idFishing).get();
			 Integer followCount=rpFollowing.findByidGroup(group.getId()).size();
		return new ClientGroup(client.getId(),client.getPassword(),client.getMail(),client.getName(),client.getSurname(),client.getImage(),
						client.getBlocked(),client.getDeleted(),client.getGoogle_signin(),group.getRegistrationNumber(),group.getName(),group.getDescription(),
						group.getLocation(),group.getContact(),group.getMembersCount(),group.getDate(),isFishingGroup,followCount);
		}
		Integer idHunting=rpHunter.findById(idUser).get().getIdGroup();
		if(idHunting!=null) {
			group=gr.findById(idHunting).get();
			 Integer followCount=rpFollowing.findByidGroup(group.getId()).size();
			
			 return new ClientGroup(client.getId(),client.getPassword(),client.getMail(),client.getName(),client.getSurname(),client.getImage(),
				client.getBlocked(),client.getDeleted(),client.getGoogle_signin(),group.getRegistrationNumber(),group.getName(),group.getDescription(),
								group.getLocation(),group.getContact(),group.getMembersCount(),group.getDate(),isFishingGroup,followCount);
		}
		
		return null;
	}
	
	public void addUserToGroup(Integer idGroup,Integer idUser)
	{
		Group g=gr.findById(idGroup).get();
		
		if(rpGFishing.findById(idGroup).isPresent()) {
		Fisherman f = rpFisherman.findById(idUser).get();
		if(f.getIdGroup()!=null)
		throw new UserAlreadyInGroupException("User is already in a group!");
		f.setIdGroup(idGroup);
		rpFisherman.save(f);
		}
		else
		{
			Hunter h=rpHunter.findById(idUser).get();
			
			if(h.getIdGroup()!=null)
				throw new UserAlreadyInGroupException("User is already in a group!");
			
			h.setIdGroup(idGroup);
			rpHunter.save(h);
		}
		g.setMembersCount(g.getMembersCount()+1);
		gr.save(g);
		rpRequest.delete(rpRequest.findByidUser(idUser).get(0));
		try {
			rpFollowing.delete(rpFollowing.findById(new FollowingId(idGroup, idUser)).get());
		}catch(Exception e)
		{}
		ZonedDateTime RSTime = ZonedDateTime.now(ZoneId.of("Europe/Belgrade"));
		LocalDateTime localDateTimeRS = RSTime.toLocalDateTime();
		notificationService.addNewNotification(new NotificationMessage(null,"Vaš zahtjev za učlanjivanje u društvo je prihvaćen!",
				idGroup,localDateTimeRS , idUser, null, false, null,false));
		
	}
	
	
	public void removeUserFromGroup(Integer idGroup,Integer idUser)
	{
		Group g=gr.findById(idGroup).get();
		
		if(rpGFishing.findById(idGroup).isPresent()) {
		Fisherman f = rpFisherman.findById(idUser).get();
		f.setIdGroup(null);
		rpFisherman.save(f);
		}
		else
		{
			Hunter h=rpHunter.findById(idUser).get();
			h.setIdGroup(null);
			if(h.getIdSection()!=null)
			{
				Section s=rpSection.findById(h.getIdSection()).get();
				s.setMembersCount(s.getMembersCount()-1);
				rpSection.save(s);
				h.setIdSection(null);
			}
			rpHunter.save(h);
		}
		g.setMembersCount(g.getMembersCount()-1);
		gr.save(g);
	}

	public List<SimpleClient> getGroupUsers(Integer idGroup) {
		
		List<SimpleClient> clients=new ArrayList<SimpleClient>();
	
		if(rpGFishing.findById(idGroup).isPresent()) {
			
			List<Fisherman> fishermans=rpFisherman.findByIdGroup(idGroup);
			
			for(Fisherman f:fishermans)
			{
				RegisteredClient client=rpClient.findById(f.getId()).get();
				clients.add(new SimpleClient(client.getId(), client.getName(), client.getSurname(), client.getImage()));
			}
			
		}
		else
		{
			List<Hunter> hunters=rpHunter.findByidGroup(idGroup);
			
			for(Hunter h:hunters)
			{
				RegisteredClient client=rpClient.findById(h.getIdUser()).get();
				clients.add(new SimpleClient(client.getId(), client.getName(), client.getSurname(), client.getImage()));
			}
			
		}
		
		
		return clients;
	}

	public List<ClientGroup> getMyGroups(Integer idUser) {
		
		List<ClientGroup> groups=new ArrayList<ClientGroup>();
		
		
		Boolean isFishingGroup=false;
		Integer idFishing=rpFisherman.findById(idUser).get().getIdGroup();
		Group group=null;
		if(idFishing!=null)
		{
			RegisteredClient client=rpClient.findById(idFishing).get();
			isFishingGroup=true;
			 group=gr.findById(idFishing).get();
			 Integer followCount=rpFollowing.findByidGroup(group.getId()).size();
		groups.add(new ClientGroup(client.getId(),client.getPassword(),client.getMail(),client.getName(),client.getSurname(),client.getImage(),
						client.getBlocked(),client.getDeleted(),client.getGoogle_signin(),group.getRegistrationNumber(),group.getName(),group.getDescription(),
						group.getLocation(),group.getContact(),group.getMembersCount(),group.getDate(),isFishingGroup,followCount));
		}
		Integer idHunting=rpHunter.findById(idUser).get().getIdGroup();
		if(idHunting!=null) {
			RegisteredClient client=rpClient.findById(idHunting).get();
			group=gr.findById(idHunting).get();
			 Integer followCount=rpFollowing.findByidGroup(group.getId()).size();
			
			 groups.add(new ClientGroup(client.getId(),client.getPassword(),client.getMail(),client.getName(),client.getSurname(),client.getImage(),
				client.getBlocked(),client.getDeleted(),client.getGoogle_signin(),group.getRegistrationNumber(),group.getName(),group.getDescription(),
								group.getLocation(),group.getContact(),group.getMembersCount(),group.getDate(),isFishingGroup,followCount));
		}
		
		return groups;
	}
	
}

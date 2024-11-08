package com.example.app.event;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.client.RegisteredClient;
import com.example.app.client.RegisteredClientRepository;
import com.example.app.fisherman.Fisherman;
import com.example.app.fisherman.FishermanRepository;
import com.example.app.following.Following;
import com.example.app.following.FollowingRepository;
import com.example.app.group.Group;
import com.example.app.group.GroupRepository;
import com.example.app.hunter.Hunter;
import com.example.app.hunter.HunterRepository;
import com.example.app.notification.NotificationMessage;
import com.example.app.notification.NotificationService;

@Service
public class EventService {
	private final EventRepository er;
	private final RegisteredClientRepository rpClient;
	private final HunterRepository rpHunter;
	private final FishermanRepository rpFisherman;
	private final GroupRepository rpGroup;
	private final FollowingRepository rpFollowing;
	private final NotificationService notificationService;
	
	@Autowired
	public EventService(EventRepository er, RegisteredClientRepository rpClient,GroupRepository rpGroup,NotificationService notificationService,
			FishermanRepository rpFisherman, HunterRepository rpHunter, FollowingRepository rpFollowing)
	{
		this.er = er;
		this.rpClient=rpClient;
		this.rpGroup=rpGroup;
		this.notificationService=notificationService;
		this.rpFisherman=rpFisherman;
		this.rpHunter=rpHunter;
		this.rpFollowing=rpFollowing;
	}

	
	public List<Event> getAllEvents(Integer idGroup) {
		List<Event> all=er.findByIdGroup(idGroup);
		for(Event event:all)
		{
			Group g=rpGroup.findById(event.getIdGroup()).get();
			RegisteredClient cl=rpClient.findById(g.getId()).get();
			
			event.setGroupName(g.getName());
			event.setGroupImage(cl.getImage());
		}
		return all;
	}


	public void deleteEvent(Integer id) {
		er.deleteById(id);
	}


	public void addNewEvent(Event event) {
		Group g=rpGroup.findById(event.getIdGroup()).get();

		List<Hunter> hunters=rpHunter.findByidGroup(event.getIdGroup());
		List<Fisherman> fishermans=rpFisherman.findByIdGroup(event.getIdGroup());
		List<Following> follows=rpFollowing.findByidGroup(g.getId());
		event=er.save(event);
		for(Hunter h:hunters) {
		addEventNotification(g.getName(), event, h.getIdUser());
		}
		for(Fisherman f:fishermans)
		{
			addEventNotification(g.getName(), event, f.getId());
		}
		for(Following f:follows)
		{
			addEventNotification(g.getName(), event, f.getIdUser());
		}
			

	}

	private void addEventNotification(String groupName,Event event,Integer idUser)
	{
		ZonedDateTime RSTime = ZonedDateTime.now(ZoneId.of("Europe/Belgrade"));
		LocalDateTime localDateTimeRS = RSTime.toLocalDateTime();
		
		notificationService.addNewNotification(new NotificationMessage(null,groupName+" Vas poziva na: "+event.getName()
		, event.getIdGroup(),localDateTimeRS,idUser , event.getImage(),true,event.getId(),false));
	}

	public void updateEvent(Event event) {
		if(event.getImage()==null)
		{
			Event ev=er.findById(event.getId()).get();
			event.setImage(ev.getImage());
		}
		er.save(event);
	}



	public Event getEventById(Integer id) {
		Event ev=er.findById(id).get();
		
		Group g=rpGroup.findById(ev.getIdGroup()).get();
		RegisteredClient cl=rpClient.findById(g.getId()).get();
		
		ev.setGroupName(g.getName());
		ev.setGroupImage(cl.getImage());
		
		return ev;
	}
}

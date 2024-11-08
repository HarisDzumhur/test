package com.example.app.notification;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.client.RegisteredClientRepository;
import com.example.app.exception.NotificationException;
import com.example.app.firebase.FirebaseAPI;
import com.example.app.group.GroupRepository;

@Service
public class NotificationService {
	private final NotificationRepository nr;
	private final RegisteredClientRepository rpClient;
	private final GroupRepository rpGroup;
	
	@Autowired
	public NotificationService(NotificationRepository nr,RegisteredClientRepository rpClient,GroupRepository rpGroup)
	{
		this.nr = nr;
		this.rpClient=rpClient;
		this.rpGroup=rpGroup;
	}

	public void addNewNotification(NotificationMessage n) {
		if (n.getContent().isBlank())
			throw new NotificationException("Content must be added!");
		
		new Thread(()->{
			
			FirebaseAPI.sendNotification("New notification!", n.getContent(), n.getIdClient());
			
		}).start();
		nr.save(n);
	}

	public void deleteNotification(Integer id) {
		nr.deleteById(id);
	}

	public List<NotificationMessage> getNewNotificationsForClient(Integer idClient) {
		List<NotificationMessage> messages=nr.findByIdClientAndIsOld(idClient,false);
		for(NotificationMessage mess:messages)
		{
			if(mess.getIsOld()==false) {
			mess.setIsOld(true);
			nr.save(mess);
			}
			mess.setGroupImage(rpClient.findById(mess.getIdGroup()).get().getImage());
			mess.setGroupName(rpGroup.findById(mess.getIdGroup()).get().getName());
		}
		return messages;
	}
	
	public List<NotificationMessage> getOldNotificationsForClient(Integer idClient) {
		List<NotificationMessage> messages=nr.findByIdClientAndIsOld(idClient,true);
		for(NotificationMessage mess:messages)
		{
			mess.setGroupImage(rpClient.findById(mess.getIdGroup()).get().getImage());
			mess.setGroupName(rpGroup.findById(mess.getIdGroup()).get().getName());
		}
		return messages;
	}
	
}

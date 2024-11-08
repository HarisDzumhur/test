package com.example.app.notification;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	private final NotificationService ns;
	
	@Autowired
	public NotificationController(NotificationService ns)
	{
		this.ns = ns;
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addNewNotification(@RequestParam Integer id,@RequestParam String content,@RequestParam Integer idGroup,@RequestParam LocalDateTime date,
			@RequestParam Integer idClient,@RequestParam Boolean isEvent,@RequestParam Integer idEvent,@RequestPart(required=false) byte[] image)
	{
		ns.addNewNotification(new NotificationMessage(id, content, idGroup, date, idClient, image,isEvent,idEvent,false));
		return new ResponseEntity<String>("Successfully added new notification!", HttpStatus.OK);
	}
	
	@GetMapping("/all/client")
	public List<NotificationMessage> getNewNotificationsForClient(@RequestParam Integer idClient)
	{
		return ns.getNewNotificationsForClient(idClient);
	}
	@GetMapping("/all/client/old")
	public List<NotificationMessage> getOldNotificationsForClient(@RequestParam Integer idClient)
	{
		return ns.getOldNotificationsForClient(idClient);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteNotification(@RequestParam Integer id)
	{
		ns.deleteNotification(id);
		return new ResponseEntity<String>("Successfully deleted notification!", HttpStatus.OK);
	}
}

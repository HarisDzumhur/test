package com.example.app.event;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {
	private final EventService es;
	
	@Autowired
	public EventController(EventService es)
	{
		this.es = es;
	}
	
	@GetMapping("/all")
	public List<Event> getAllEvents(@RequestParam Integer idGroup)
	{
		return es.getAllEvents(idGroup);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addNewEvent(@RequestParam String name, @RequestParam LocalDateTime date, @RequestParam String description,
			@RequestPart byte[] image, @RequestParam Integer idGroup, @RequestParam Double latitude,@RequestParam Double longitude)
	{
		es.addNewEvent(new Event(null, name, date, description, image, idGroup, latitude,longitude));
		return new ResponseEntity<String>("Successfully added new event!", HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updateEvent(@RequestParam Integer id, @RequestParam String name, @RequestParam LocalDateTime date, @RequestParam String description,
			@RequestPart(required=false) byte[] image, @RequestParam Integer idGroup,@RequestParam Double latitude,@RequestParam Double longitude)
	{
		es.updateEvent(new Event(id, name, date, description, image, idGroup, latitude,longitude));
		return new ResponseEntity<String>("Successfully updated event!", HttpStatus.OK);
	}
	
	@PutMapping("/updateevent")
	public ResponseEntity<String> update(@RequestParam Integer id, @RequestParam String name, @RequestParam LocalDateTime date, @RequestParam String description,
	 @RequestParam Integer idGroup,@RequestParam Double latitude,@RequestParam Double longitude)
	{
		es.updateEvent(new Event(id, name, date, description, null, idGroup, latitude,longitude));
		return new ResponseEntity<String>("Successfully updated event!", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteEvent(@RequestParam Integer id)
	{
		es.deleteEvent(id);
		return new ResponseEntity<String>("Successfully deleted event!", HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Event> getEventById(@RequestParam Integer id)
	{
		return new ResponseEntity<Event>(es.getEventById(id),HttpStatus.OK);
	}
	
}

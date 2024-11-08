package com.example.app.calendar;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/calendar")
public class HuntingCalendarController {

	
	private final HuntingCalendarService hc;
	
	@Autowired
	public HuntingCalendarController(HuntingCalendarService hc)
	{
		this.hc=hc;
	}
	
	@GetMapping("/all/year")
	public List<HuntingCalendar> getCalendars(@RequestParam Integer year)
	{
		return hc.getCalendarsByYear(year);
	}
	
	@GetMapping("/all")
	public List<HuntingCalendar> getCalendars()
	{
		return hc.getCalendars();
	}
	
	@PostMapping("/add")
	public ResponseEntity<HuntingCalendar> addCalendar(@RequestBody HuntingCalendar cal)
	{
		return new ResponseEntity<HuntingCalendar>(hc.addCalendar(cal), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<HuntingCalendar> updateCalendar(@RequestBody HuntingCalendar cal)
	{
		return new ResponseEntity<HuntingCalendar>(hc.updateCalender(cal),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCalendar(@RequestParam Integer year, @RequestParam String animal)
	{
		hc.deleteCalender(year, animal.replace("_", " "));
		return new ResponseEntity<>("Successfully deleted the calendar!",HttpStatus.OK);
	}
	
}

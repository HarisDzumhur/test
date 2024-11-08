package com.example.app.calendar;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.exception.HuntingCalendarException;

@Service
public class HuntingCalendarService {

	private final HuntingCalendarRepository rp;
	
	
	@Autowired
	public HuntingCalendarService(HuntingCalendarRepository rp)
	{
		this.rp=rp;
	}
	
	public HuntingCalendar addCalendar(HuntingCalendar cal)
	{
		Optional<HuntingCalendar> hc = rp.findById(new HuntingCalendarId(cal.getYear(), cal.getAnimal()));
		if (hc.isEmpty())
			return rp.save(cal);
		else
			throw new HuntingCalendarException("That record is already present in the table.");
	}
	
	public List<HuntingCalendar> getCalendars()
	{
		return rp.findAll();
	}
	
	public void deleteCalender(Integer year, String animal)
	{
		rp.deleteById(new HuntingCalendarId(year, animal));
	}

	public List<HuntingCalendar> getCalendarsByYear(Integer year) {
		
		return rp.findByYear(year);
	}

	public HuntingCalendar updateCalender(HuntingCalendar cal) {
		return rp.save(cal);
	}
	
}

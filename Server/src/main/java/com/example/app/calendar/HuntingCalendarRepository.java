package com.example.app.calendar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuntingCalendarRepository extends JpaRepository<HuntingCalendar, HuntingCalendarId>{
	public List<HuntingCalendar> findByYear(Integer year);
}

package com.example.app.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.exception.UserNotFoundException;

@Service
public class LocationService {

	private final LocationRepository rp;
	
	@Autowired
	public LocationService(LocationRepository rp)
	{
		this.rp=rp;
	}
	
	public Location getUserLocation(Integer id)
	{
		try {
		return rp.findById(id).get();
		}catch(Exception ex)
		{
			throw new UserNotFoundException("User with that id doesn't exist!");
		}
	}
	
	public Location addUserLocation(Location loc)
	{
		return rp.save(loc);
	}
	
}

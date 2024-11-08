package com.example.app.sharing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.exception.UserNotFoundException;

@Service
public class LocationSharingService {

	private final LocationSharingRepository rp;
	
	@Autowired
	public LocationSharingService(LocationSharingRepository rp)
	{
		this.rp=rp;
	}
	
	public LocationSharing getByIds(Integer id1,Integer id2)
	{
		try {
		return rp.findById(new LocationSharingId(id1,id2)).get();
		}catch(Exception ex)
		{
			throw new UserNotFoundException("User doesn't exist!");
		}
	}
	
	public LocationSharing add(LocationSharing sh)
	{
		return rp.save(sh);
	}

	public void removeById(Integer id1) {
		
		List<LocationSharing> list1=rp.findByidHunter1(id1);
		List<LocationSharing> list2=rp.findByidHunter2(id1);
	
		for(LocationSharing s:list1)
		{
			rp.delete(s);
		}
		for(LocationSharing s:list2)
		{
			rp.delete(s);
		}
	}
	
}

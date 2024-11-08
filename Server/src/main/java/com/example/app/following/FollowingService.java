package com.example.app.following;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.exception.UserAlreadyInGroupException;

@Service
public class FollowingService {

	
	private final FollowingRepository rp;
	
	@Autowired
	public FollowingService(FollowingRepository rp)
	{
		this.rp=rp;
	}

	public void add(Following follow) {
		if(rp.findById(new FollowingId(follow.getIdGroup(),follow.getIdUser())).isPresent())
		{
			throw new UserAlreadyInGroupException("You are already following this group!");
		}
		rp.save(follow);
	}
	
	public void remove(Following follow)
	{
		rp.delete(follow);
	}

	public List<Integer> getMyFollowingIds(Integer idUser) {
		
		List<Following> follows=rp.findByidUser(idUser);
		List<Integer> ids=new ArrayList<Integer>();
		for(Following f:follows)
		{
			ids.add(f.getIdGroup());
		}
		return ids;
	}
	
	
}

package com.example.app.group.request;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.client.RegisteredClient;
import com.example.app.client.RegisteredClientRepository;
import com.example.app.exception.RequestAlreadySentException;
import com.example.app.exception.UserAlreadyInGroupException;
import com.example.app.fisherman.Fisherman;
import com.example.app.fisherman.FishermanRepository;
import com.example.app.group.fish.FishingGroupRepository;
import com.example.app.group.hunt.HuntingGroupRepository;
import com.example.app.hunter.Hunter;
import com.example.app.hunter.HunterRepository;

@Service
public class GroupRequestService {

	private final GroupRequestRepository rp;
	private final RegisteredClientRepository rpClient;
	private final HunterRepository rpHunter;
	private final FishermanRepository rpFisherman;
	private final FishingGroupRepository rpFishingGroup;
	private final HuntingGroupRepository rpHuntingGroup;
	
	
	@Autowired
	public GroupRequestService(GroupRequestRepository rp, RegisteredClientRepository rpClient,HunterRepository rpHunter,FishermanRepository rpFisherman,
			FishingGroupRepository rpFishingGroup,HuntingGroupRepository rpHuntingGroup)
	{
		this.rp=rp;
		this.rpClient=rpClient;
		this.rpHunter=rpHunter;
		this.rpFisherman=rpFisherman;
		this.rpFishingGroup=rpFishingGroup;
		this.rpHuntingGroup=rpHuntingGroup;
	}
	
	
	public GroupRequest addRequest(GroupRequest request)
	{
		List<GroupRequest> list=rp.findByidGroup(request.getIdGroup());
		boolean found=false;
		if(rpFishingGroup.findById(request.getIdGroup()).isPresent()) {
			Fisherman f=rpFisherman.findById(request.getIdUser()).get();
			if(f.getIdGroup()!=null)
				throw new UserAlreadyInGroupException("User is already in a group!");
		}
		if(rpHuntingGroup.findById(request.getIdGroup()).isPresent()) {
		Hunter h=rpHunter.findById(request.getIdUser()).get();
		if(h.getIdGroup()!=null)
		throw new UserAlreadyInGroupException("User is already in a group!");
		}
		
		for(GroupRequest req:list)
		{
			
			if(req.getIdUser()==request.getIdUser())
			{
				found=true;
				break;
			}
		}
		request.setDate(LocalDateTime.now());
		if(found==false)
		return rp.save(request);
		else
			throw new RequestAlreadySentException("The request to this group has already been sent!");
	}
	
	public List<GroupRequest> getRequestsByIdGroup(Integer idGroup)
	{
		List<GroupRequest> list=rp.findByidGroup(idGroup);
		
		for(GroupRequest request:list)
		{
			RegisteredClient client=rpClient.findById(request.getIdUser()).get();
			request.setUserName(client.getName());
			request.setUserSurname(client.getSurname());
			request.setUserImage(client.getImage());
		}
		
		return list;
	}
	
	public void deleteGroupRequest(Integer id)
	{
		rp.deleteById(id);
	}
	
}

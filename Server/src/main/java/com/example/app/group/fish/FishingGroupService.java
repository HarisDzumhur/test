package com.example.app.group.fish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FishingGroupService {
	private final FishingGroupRepository fgr;
	
	@Autowired
	public FishingGroupService(FishingGroupRepository fgr)
	{
		this.fgr = fgr;
	}
	
	public void addNewFishingGroup(FishingGroup fg) {
		fgr.save(fg);
	}
}

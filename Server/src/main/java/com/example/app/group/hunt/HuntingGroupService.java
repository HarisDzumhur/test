package com.example.app.group.hunt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HuntingGroupService {
	private final HuntingGroupRepository hgr;
	
	@Autowired
	public HuntingGroupService(HuntingGroupRepository hgr)
	{
		this.hgr = hgr;
	}

	public void addNewHuntingGroup(HuntingGroup hg) {
		hgr.save(hg);
	}
}

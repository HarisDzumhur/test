package com.example.app.group.fish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group/fish")
public class FishingGroupController {
	private final FishingGroupService fgs;
	
	@Autowired
	public FishingGroupController(FishingGroupService fgs)
	{
		this.fgs = fgs;
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addNewHuntingGroup(@RequestParam Integer id)
	{
		fgs.addNewFishingGroup(new FishingGroup(id));
		return new ResponseEntity<String>("Successfully added new fishing group!", HttpStatus.OK);
	}
}

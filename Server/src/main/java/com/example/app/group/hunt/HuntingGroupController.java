package com.example.app.group.hunt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group/hunt")
public class HuntingGroupController {
	private final HuntingGroupService hgs;
	
	@Autowired
	public HuntingGroupController(HuntingGroupService hgs)
	{
		this.hgs = hgs;
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addNewHuntingGroup(@RequestParam Integer id)
	{
		hgs.addNewHuntingGroup(new HuntingGroup(id));
		return new ResponseEntity<String>("Successfully added new hunting group!", HttpStatus.OK);
	}
}

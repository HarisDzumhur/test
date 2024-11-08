package com.example.app.group.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group/request")
public class GroupRequestController {

	private final GroupRequestService gs;
	
	
	@Autowired
	public GroupRequestController(GroupRequestService gs)
	{
		this.gs=gs;
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<GroupRequest> add(@RequestBody GroupRequest request)
	{
		return new ResponseEntity<GroupRequest>(gs.addRequest(request),HttpStatus.OK);
	}
	
	@DeleteMapping("/remove")
	public ResponseEntity<String> removeRequest(@RequestParam Integer id)
	{
		gs.deleteGroupRequest(id);
		return new ResponseEntity<String>("Successfully deleted request!",HttpStatus.OK);
	}
	
	
	@GetMapping("/allgroup")
	public List<GroupRequest> getGroupRequests(@RequestParam Integer idGroup)
	{
		return gs.getRequestsByIdGroup(idGroup);
	}

}

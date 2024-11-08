package com.example.app.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.client.ClientGroup;
import com.example.app.client.RegisteredClient;
import com.example.app.client.SimpleClient;

@RestController
@RequestMapping("/group")
public class GroupController {
	private final GroupService gs;
	
	@Autowired
	public GroupController(GroupService gs)
	{
		this.gs = gs;
	}
	
	@PostMapping("/add")
	public ResponseEntity<Group> addNewGroup(@RequestBody Group group)
	{
		return new ResponseEntity<Group>(gs.saveGroup(group), HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Group> updateGroup(@RequestBody Group group)
	{
		return new ResponseEntity<Group>(gs.saveGroup(group), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteGroup(@RequestBody Integer id)
	{
		gs.deleteGroup(id);
		return new ResponseEntity<String>("Successfully deleted group!", HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public List<ClientGroup> getAllGroups()
	{
		return gs.getAllGroups();
	}
	
	@GetMapping("/my")
	public List<ClientGroup> getMyGroups(@RequestParam Integer idUser)
	{
		return gs.getMyGroups(idUser);
	}
	
	@PostMapping("/user/add")
	public ResponseEntity<String> addUserToGroup(@RequestParam Integer idGroup,@RequestParam Integer idUser)
	{
		gs.addUserToGroup(idGroup, idUser);
		return new ResponseEntity<String>("Successfully added user to group",HttpStatus.OK);
	}
	
	@DeleteMapping("/user/remove")
	public ResponseEntity<String> removeUserFromGroup(@RequestParam Integer idGroup,@RequestParam Integer idUser)
	{
		gs.removeUserFromGroup(idGroup, idUser);
		return new ResponseEntity<String>("Successfully removed user from group",HttpStatus.OK);
	}
	
	@GetMapping("/members")
	public List<SimpleClient> getGroupUsers(@RequestParam Integer idGroup)
	{
		return gs.getGroupUsers(idGroup);
	}
	
	
}

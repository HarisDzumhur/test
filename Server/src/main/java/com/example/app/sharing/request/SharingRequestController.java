package com.example.app.sharing.request;

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

import com.example.app.sharing.LocationSharing;

@RestController
@RequestMapping("/request")
public class SharingRequestController {
	
	private final SharingRequestService rs;
	
	@Autowired
	public SharingRequestController(SharingRequestService rs)
	{
		this.rs=rs;
	}
	
	
	@GetMapping("/my")
	public List<SharingRequest> getMyRequests(@RequestParam Integer id2)
	{
		return rs.getMyRequests(id2);
	}
	
	@PostMapping("/add")
	public ResponseEntity<SharingRequest> add(@RequestBody SharingRequest sharing)
	{
		return new ResponseEntity<>(rs.add(sharing),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> remove(@RequestBody SharingRequest sharing)
	{
		rs.remove(sharing);
		return new ResponseEntity<>("Removed sharing request!",HttpStatus.OK);
	}
}

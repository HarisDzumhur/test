package com.example.app.reply;

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


@RestController
@RequestMapping("/reply")
public class ForumReplyController {

	
	private final ForumReplyService fr;
	
	@Autowired
	public ForumReplyController(ForumReplyService fr)
	{
		this.fr=fr;
	}
	
	@GetMapping("/idtopic")
	public List<ForumReply> getRepliesByIdTopic(@RequestParam Integer idTopic)
	{
		return fr.getRepliesForTopic(idTopic);
	}
	
	@PostMapping("/add")
	public ResponseEntity<ForumReply> addReply(@RequestBody ForumReply reply)
	{
		return new ResponseEntity<ForumReply>(fr.addNewReply(reply), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteReply(@RequestParam Integer idReply)
	{
		fr.removeById(idReply);
		return new ResponseEntity<>("Successfully deleted reply!",HttpStatus.OK);
	}
	
	@PutMapping("/report")
	public ResponseEntity<ForumReply> reportReply(@RequestParam Integer idReply)
	{
		return new ResponseEntity<ForumReply>(fr.reportReply(idReply),HttpStatus.OK);
	}
	@GetMapping("/reported")
	public List<ForumReply> getReportedReplies()
	{
		return fr.getReportedReplies();
	}
	
	@GetMapping("/all")
	public List<ForumReply> getAllReplies()
	{
		return fr.getAllReplies();
	}
		
}

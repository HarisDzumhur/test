package com.example.app.reply;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.example.app.client.RegisteredClient;
import com.example.app.client.RegisteredClientRepository;
import com.example.app.forum.Forum;
import com.example.app.forum.ForumRepository;
import com.example.app.trophy.Trophy;

@Service
public class ForumReplyService {

	private final ForumReplyRepository rp;
	private final RegisteredClientRepository rpClient;
	private final ForumRepository rpForum;
	
	@Autowired
	public ForumReplyService(ForumReplyRepository rp,RegisteredClientRepository rpClient,ForumRepository rpForum)
	{
		this.rp=rp;
		this.rpClient=rpClient;
		this.rpForum=rpForum;
	}
	
	public List<ForumReply> getRepliesForTopic(Integer idTopic)
	{
		List<ForumReply> replies=rp.findByIdTopic(idTopic);
		
		for(ForumReply r:replies)
		{
			RegisteredClient client=rpClient.findById(r.getIdResponseUser()).get();
			r.setUserName(client.getName());
			r.setUserSurname(client.getSurname());
			r.setUserImage(client.getImage());
		}
		Collections.sort(replies, Comparator.comparing(ForumReply::getDate).reversed());
		return replies;
	}
	
	public ForumReply addNewReply(ForumReply reply)
	{
		return rp.save(reply);
	}
	public void removeById(Integer idReply)
	{
		rp.deleteById(idReply);
	}

	public List<ForumReply> getReportedReplies() {
		return rp.findByreported(true);
	}

	public ForumReply reportReply(Integer idReply) {
		ForumReply reply=rp.findById(idReply).get();
		reply.setReported(true);
		rp.save(reply);
		return reply;
	}

	public List<ForumReply> getAllReplies() {
			List<ForumReply> replies=rp.findAll();
		
		for(ForumReply r:replies)
		{
			RegisteredClient client=rpClient.findById(r.getIdResponseUser()).get();
			r.setUserName(client.getName());
			r.setUserSurname(client.getSurname());
			r.setUserImage(client.getImage());
			Forum f=rpForum.findById(r.getIdTopic()).get();
			r.setTopicName(f.getTitle());
		}
		Collections.sort(replies, Comparator.comparing(ForumReply::getDate).reversed());
		return replies;
	}
	
}

package com.example.app.forum;



import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.client.RegisteredClient;
import com.example.app.client.RegisteredClientRepository;
import com.example.app.reply.ForumReply;
import com.example.app.reply.ForumReplyRepository;



@Service
public class ForumService {

	private final ForumRepository rp;
	private final RegisteredClientRepository rpCLient;
	private final ForumReplyRepository rpReply;
	
	@Autowired
	public ForumService(ForumRepository rp,RegisteredClientRepository rpClient,ForumReplyRepository rpReply)
	{
		this.rp=rp;
		this.rpCLient=rpClient;
		this.rpReply=rpReply;
	}
	public List<Forum> getAllForums() {
		List<Forum> forums=rp.findAll();
		for(Forum f:forums)
		{
			RegisteredClient client=rpCLient.findById(f.getIdClient()).get();
			int number=rpReply.findByIdTopic(f.getId()).size();
			f.setUserName(client.getName());
			f.setUserSurname(client.getSurname());
			f.setUserImage(client.getImage());
			f.setReplyNumber(number);
		}
		
		Collections.sort(forums, Comparator.comparing(Forum::getDate).reversed());
		
		return forums;
	}

	public Forum addNewTopic(Forum f)
	{
		RegisteredClient client=rpCLient.findById(f.getIdClient()).get();
		f.setUserName(client.getName());
		f.setUserSurname(client.getSurname());
		f.setUserImage(client.getImage());
		f.setReplyNumber(0);
		return rp.save(f);
	}
	public List<Forum> getMyTopics(Integer idClient) {
		List<Forum> forums=rp.findByIdClient(idClient);
		RegisteredClient client=rpCLient.findById(idClient).get();
		for(Forum f:forums)
		{
			int number=rpReply.findByIdTopic(f.getId()).size();
			f.setUserName(client.getName());
			f.setUserSurname(client.getSurname());
			f.setUserImage(client.getImage());
			f.setReplyNumber(number);
		}
		Collections.sort(forums, Comparator.comparing(Forum::getDate).reversed());
		return forums;
	}
	
}

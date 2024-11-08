package com.example.app.taghasforum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagHasForumService {

	private final TagHasForumRepository rp;
	
	@Autowired
	public TagHasForumService(TagHasForumRepository rp)
	{
		this.rp=rp;
	}
	
	public TagHasForum add(TagHasForum tf)
	{
		return rp.save(tf);
	}
	
	public List<TagHasForum> all()
	{
		List<TagHasForum> list=rp.findAll();
		
		for(TagHasForum t:list)
		{
			int counter=getByTagName(t.getTagName()).size();
			t.setNumTopics(counter);
		}
		
		return list;
	}
	public List<TagHasForum> getByIdTopic(Integer idTopic)
	{
		return rp.findByidTopic(idTopic);
	}
	
	public List<TagHasForum> getByTagName(String tagName)
	{
		return rp.findBytagName(tagName);
	}
	
}

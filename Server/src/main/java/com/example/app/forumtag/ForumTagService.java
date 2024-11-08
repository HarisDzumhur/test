package com.example.app.forumtag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.exception.TagException;

@Service
public class ForumTagService {
	
	private final ForumTagRepository rp;
	
	@Autowired
	public ForumTagService(ForumTagRepository rp)
	{
		this.rp=rp;
	}
	
	public List<ForumTag> getAllTags()
	{
		return rp.findAll();
	}
	
	public ForumTag add(ForumTag tag)
	{
		if(rp.findById(tag.getName()).isPresent())
		{
			throw new TagException("Tag with that name already exists!");
		}
		rp.save(tag);
		return tag;
	}
	
}

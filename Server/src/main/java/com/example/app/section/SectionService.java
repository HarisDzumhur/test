package com.example.app.section;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.client.RegisteredClient;
import com.example.app.client.RegisteredClientRepository;
import com.example.app.client.SimpleClient;
import com.example.app.exception.SectionException;
import com.example.app.group.hunt.HuntingGroup;
import com.example.app.group.hunt.HuntingGroupRepository;
import com.example.app.hunter.Hunter;
import com.example.app.hunter.HunterRepository;

@Service
public class SectionService {
	private final SectionRepository sr;
	private final RegisteredClientRepository rpClient;
	private final HunterRepository rpHunter;
	
	@Autowired
	public SectionService(SectionRepository sr,RegisteredClientRepository rpClient,
	 HunterRepository rpHunter)
	{
		this.sr = sr;
		this.rpClient=rpClient;
		this.rpHunter=rpHunter;
	}

	public List<Section> getAllSections(Integer idGroup) {
		return sr.findByIdGroup(idGroup);
	}

	public Section saveSection(Section section) {
		if (section.getMembersCount() == null || section.getMembersCount() < 0)
			throw new SectionException("Members field is invalid!");
		if (section.getDescription().isBlank())
			throw new SectionException("Description is blank!");
		if (section.getName().isBlank())
			throw new SectionException("Name is blank!");
		if (section.getImage() == null)
			throw new SectionException("Image is empty!");
		
		List<Section> list = sr.findByName(section.getName());
		if (!list.isEmpty())
			throw new SectionException("Name already exists!");
		
			
		return sr.save(section);
	}

	public void deleteSection(Integer id) {
		
		List<Hunter> allHunters=rpHunter.findAll();
		for(Hunter h:allHunters)
		{
			if(h.getIdSection()!=null && h.getIdSection()==id)
			{
				h.setIdSection(null);
				rpHunter.save(h);
			}
		}
		sr.deleteById(id);
	}
	
	public Section getMySection(Integer id)
	{
		Hunter h=rpHunter.findById(id).get();
		if(h.getIdSection()!=null)
		return sr.findById(h.getIdSection()).get();
		
		return null;
	}

	public List<SimpleClient> getNoSectionUsers(Integer idGroup) {
		
		List<SimpleClient> list=new ArrayList<SimpleClient>();
		
		List<Hunter> allHunters=rpHunter.findAll();
		for(Hunter h:allHunters)
		{
			if(h.getIdSection()==null && h.getIdGroup()==idGroup)
			{
				RegisteredClient client=rpClient.findById(h.getIdUser()).get();
				if(client.getBlocked()==false && client.getDeleted()==false)
				 list.add(new SimpleClient(h.getIdUser(),client.getName() , client.getSurname(), client.getImage()));
			}
		}
		
		return list;
	}

	
	public void updateSection(Section section) {
		
		if(section.getImage()==null)
		{
			section.setImage(sr.findById(section.getId()).get().getImage());
		}
		sr.save(section);
	}

	public Section getSectionById(Integer id) {
		return sr.findById(id).get();
	}
}

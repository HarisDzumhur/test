package com.example.app.hunter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.client.RegisteredClient;
import com.example.app.client.RegisteredClientRepository;
import com.example.app.client.SimpleClient;
import com.example.app.exception.UserNotFoundException;
import com.example.app.group.Group;
import com.example.app.group.GroupRepository;
import com.example.app.section.Section;
import com.example.app.section.SectionRepository;

@Service
public class HunterService {

	private final HunterRepository rp;
	private final GroupRepository rpGroup;
	private final SectionRepository rpSection;
	private final RegisteredClientRepository rpClient;
	
	@Autowired
	public HunterService(HunterRepository rp,GroupRepository rpGroup,SectionRepository rpSection,RegisteredClientRepository rpClient)
	{
		this.rp=rp;
		this.rpGroup=rpGroup;
		this.rpSection=rpSection;
		this.rpClient=rpClient;
	}
	
	
	public Hunter addHunter(Integer idUser)
	{
		Hunter h=new Hunter(idUser,null,null);
		try {
			return rp.save(h);
			}catch(Exception ex)
			{
				throw new UserNotFoundException("Hunter with given id is not a user!");
			}
	}

	
	public List<SimpleClient> getHuntersFromSection(Integer idSection)
	{
		try {
		List<Hunter> hList=rp.findByidSection(idSection);
		List<SimpleClient> list=new ArrayList<SimpleClient>();
		for(Hunter h:hList)
		{
			RegisteredClient client=rpClient.findById(h.getIdUser()).get();
			list.add(new SimpleClient(client.getId(), client.getName(), client.getSurname(), client.getImage()));
		}
		return list;
		}catch(Exception ex) {
			throw new UserNotFoundException("Section doesn't exist!");
		}
	}


	public void removeHunterFromSection(Integer id) {
		Hunter h = rp.findById(id).get();
		Section s=rpSection.findById(h.getIdSection()).get();
	
		s.setMembersCount(s.getMembersCount()-1);
		rpSection.save(s);
		
		h.setIdSection(null);
	
		rp.save(h);
	}
	public Hunter removeHunterFromGroup(Integer id)
	{
		Hunter h = rp.findById(id).get();
		Group g=rpGroup.findById(h.getIdGroup()).get();
		g.setMembersCount(g.getMembersCount()-1);
		rpGroup.save(g);
		h.setIdGroup(null);
		if(h.getIdSection()!=null)
		{
			Section s=rpSection.findById(h.getIdSection()).get();
			s.setMembersCount(s.getMembersCount()-1);
			rpSection.save(s);
		}
		rpGroup.save(g);
		
		return h;
	}

	public Hunter addUserToSection(Integer id, Integer idSection) {
		Hunter h = rp.findById(id).get();
		h.setIdSection(idSection);
		Section s=rpSection.findById(idSection).get();
		
		s.setMembersCount(s.getMembersCount()+1);
		rpSection.save(s);
		
		return rp.save(h);
	}

	public Hunter addHunterToGroup(Integer id, Integer idGroup) {
		
		Hunter f = rp.findById(id).get();
		f.setIdGroup(idGroup);
		Group g=rpGroup.findById(idGroup).get();
		g.setMembersCount(g.getMembersCount()+1);
		rpGroup.save(g);
		return rp.save(f);
	}

	public void addListOfUsersToSection(Integer idSection,List<Integer> list) {
		for(Integer i:list)
		{
			addUserToSection(i, idSection);
		}

	}
	
	
}

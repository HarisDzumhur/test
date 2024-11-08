package com.example.app.fisherman;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.app.exception.UserNotFoundException;
import com.example.app.group.Group;
import com.example.app.group.GroupRepository;

@Service
public class FishermanService {

	private final FishermanRepository rp;
	private final GroupRepository rpGroup;
	
	@Autowired
	public FishermanService(FishermanRepository rp,GroupRepository rpGroup)
	{
		this.rp=rp;
		this.rpGroup=rpGroup;
	}
	
	public List<Fisherman> getAll()
	{
		return rp.findAll();
	}
	public Fisherman addUser(Integer id)
	{			
		Fisherman u=new Fisherman(id, null);
		try {
			return rp.save(u);
			}catch(Exception ex)
			{
				throw new UserNotFoundException("Fisherman with given id is not a user!");
			}
	}
	public Fisherman getById(Integer id)
	{
		try {
		Fisherman u=rp.findById(id).get();
		return u;
		}catch(Exception ex) {
			throw new UserNotFoundException("Fisherman with given id doesn't exist!");
		}
	}

	public List<Fisherman> getByIdGroup(Integer idGroup) {
		return rp.findByIdGroup(idGroup);
	}

	public void deleteFisherman(Integer id) {
		rp.deleteById(id);
	}

	public Fisherman removeFisherman(Integer id) {
		Fisherman f = rp.findById(id).get();
		Group g=rpGroup.findById(f.getIdGroup()).get();
		f.setIdGroup(null);
		g.setMembersCount(g.getMembersCount()-1);
		rpGroup.save(g);
		return rp.save(f);
	}
	public Fisherman addFishermanToGroup(Integer id,Integer idGroup)
	{
		Fisherman f = rp.findById(id).get();
		f.setIdGroup(idGroup);
		Group g=rpGroup.findById(idGroup).get();
		g.setMembersCount(g.getMembersCount()+1);
		rpGroup.save(g);
		return rp.save(f);
	}

}

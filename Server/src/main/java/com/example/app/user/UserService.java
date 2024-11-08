package com.example.app.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.exception.UserNotFoundException;

@Service
public class UserService {

	private final UserRepository rp;
	
	@Autowired
	public UserService(UserRepository rp)
	{
		this.rp=rp;
	}
	
	public List<User> getAll()
	{
		return rp.findAll();
	}
	public User addUser(Integer id)
	{
		User u=new User(id);
		try {
		rp.save(u);
		return u;
		}catch(Exception ex)
		{
			throw new UserNotFoundException("User with given id is not a registered client!");
		}
	}
	public User getById(Integer id)
	{
		try {
		User u=rp.findById(id).get();
		return u;
		}catch(Exception ex) {
			throw new UserNotFoundException("User with given id doesn't exist!");
		}
	}
	
}

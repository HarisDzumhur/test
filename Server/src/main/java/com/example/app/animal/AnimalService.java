package com.example.app.animal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.exception.AnimalException;

@Service
public class AnimalService {

	private final AnimalRepository rp;
	
	@Autowired
	public AnimalService(AnimalRepository rp)
	{
		this.rp=rp;
	}
	
	public String add(Animal a)
	{
		if (rp.findById(a.getName()).isEmpty()) {
			rp.save(a);
			return "Successfully added a new animal.";
		}
		else
			throw new AnimalException("Animal already exists.");
	}
	public List<Animal> getAll()
	{
		return rp.findAll();
	}
	
}

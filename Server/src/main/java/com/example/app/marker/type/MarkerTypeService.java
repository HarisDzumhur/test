package com.example.app.marker.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.exception.MarkerException;
import com.example.app.exception.TagException;

@Service
public class MarkerTypeService {

	private final MarkerTypeRepository rp;
	
	@Autowired
	public MarkerTypeService(MarkerTypeRepository rp)
	{
		this.rp = rp;
	}
	
	
	public List<MarkerType> getMarkerTypes()
	{
		return rp.findAll();
	}
	public MarkerType addMarkerType(MarkerType mt)
	{
		if(rp.findByName(mt.getName()).size()!=0)
			throw new MarkerException("Marker with that name already exists!");
		
		return rp.save(mt);
	}
	
	
}

package com.example.app.marker;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.client.RegisteredClient;
import com.example.app.client.RegisteredClientRepository;

@Service
public class MapMarkerService {

	private final MapMarkerRepository rp;
	private final RegisteredClientRepository rpClient;
	
	@Autowired
	public MapMarkerService(MapMarkerRepository rp,RegisteredClientRepository rpClient)
	{
		this.rp=rp;
		this.rpClient=rpClient;
	}
	
	public MapMarker addMarker(MapMarker marker)
	{
		return rp.save(marker);
	}
	
	public List<MapMarker> getPublicMarkers()
	{
		List<MapMarker> markers=rp.findByIsPublic(true);
		List<MapMarker> copy=new ArrayList<MapMarker>();
		
		for(MapMarker marker:markers)
		{
			RegisteredClient client=rpClient.findById(marker.getIdUser()).get();
			if(client.getDeleted()==false && client.getBlocked()==false)
				copy.add(marker);
				
		}
		
		return copy;
	}
	public List<MapMarker> getUserMarkers(Integer idUser)
	{
		List<MapMarker> markers=rp.findByIdUser(idUser);
		for(MapMarker m:markers)
		{
			RegisteredClient client=rpClient.findById(idUser).get();
			m.setUserName(client.getName());
			m.setUserSurname(client.getSurname());
		}
		return markers;
	}
	
	public void removeMarker(Integer id)
	{
		Optional<MapMarker> ma=rp.findById(id);
		MapMarker marker=ma.get();
		rp.delete(marker);
	}
	public MapMarker updateMarkerVisibility(Integer id,Boolean isPublic)
	{
		Optional<MapMarker> ma=rp.findById(id);
		MapMarker marker=ma.get();
		
		marker.setIsPublic(isPublic);
		return rp.save(marker);
	}

	public List<MapMarker> getUserAndPublicMarkers(Integer idUser) {
		List<MapMarker> publicMarkers=getPublicMarkers();
		List<MapMarker> userMarkers=getUserMarkers(idUser);
		
		for(MapMarker marker:publicMarkers)
		{
			if(marker.getIdUser()!=idUser)
			{
				userMarkers.add(marker);
			}
			
		}
		for(MapMarker m:userMarkers)
		{
			RegisteredClient client=rpClient.findById(m.getIdUser()).get();
			m.setUserName(client.getName());
			m.setUserSurname(client.getSurname());
		}
		
		return userMarkers;
	}
}

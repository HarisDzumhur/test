package com.example.app.trophy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.animal.Animal;
import com.example.app.animal.AnimalRepository;
import com.example.app.client.RegisteredClient;
import com.example.app.client.RegisteredClientRepository;
import com.example.app.problem.Problem;

@Service
public class TrophyService {
	private final TrophyRepository tr;
	private final RegisteredClientRepository rpClient;
	private final AnimalRepository rpAnimal;
	
	@Autowired
	public TrophyService(TrophyRepository tr,RegisteredClientRepository rpClient,AnimalRepository rpAnimal)
	{
		this.tr = tr;
		this.rpClient=rpClient;
		this.rpAnimal=rpAnimal;
	}



	public Trophy addTrophy(Trophy trophy) {
		return tr.save(trophy);
	}

	public void deleteTrophy(Integer id) {
		tr.deleteById(id);
	}

	public Trophy update(Trophy trophy) {
		if(trophy.getImage()==null) {
			Trophy t=tr.findById(trophy.getId()).get();
			trophy.setImage(t.getImage());
		}
		return tr.save(trophy);
	}


	
	
	public Trophy getTrophyById(Integer id)
	{
		Trophy t=tr.findById(id).get();
		RegisteredClient client=rpClient.findById(t.getIdClient()).get();
		Animal a=rpAnimal.findById(t.getAnimal()).get();
		
		t.setUserName(client.getName());
		t.setUserSurname(client.getSurname());
		t.setUserImage(client.getImage());
		t.setIsFish(a.getIsFish());
		return t;
	}
	
	public List<Trophy> getPublicTrophies() {
		List<Trophy> trophies=tr.findByIsPublic(true);
		
		for(Trophy t:trophies)
		{
			RegisteredClient client=rpClient.findById(t.getIdClient()).get();
			t.setUserName(client.getName());
			t.setUserSurname(client.getSurname());
			t.setUserImage(null);
			t.setImage(null);
			Animal a=rpAnimal.findById(t.getAnimal()).get();
			t.setIsFish(a.getIsFish());
		}
		
		return checkClient(trophies);
	}
	
	public List<Trophy> getPublicHuntingOrFishingTrophies(boolean isFish,Integer lastId,Integer chunkSize,LocalDateTime dateOfLastPost) {
		List<Trophy> trophies=tr.findByIsPublic(true);
		return filter(isFish, lastId, chunkSize, dateOfLastPost, trophies);
	}
	
	public List<Trophy> getMyHuntingOrFishingTrophies(Integer idClient,boolean isFish,Integer lastId,Integer chunkSize,LocalDateTime dateOfLastPost) {
		List<Trophy> trophies=tr.findByIdClient(idClient);
		return filter(isFish, lastId, chunkSize, dateOfLastPost, trophies);
	}
	
	public List<Trophy> filter(boolean isFish,Integer lastId,Integer chunkSize,LocalDateTime dateOfLastPost,List<Trophy> trophies)
	{
		List<Trophy> helper1=new ArrayList<Trophy>();
		for(Trophy t:trophies)
		{
			RegisteredClient client=rpClient.findById(t.getIdClient()).get();
			Animal a=rpAnimal.findById(t.getAnimal()).get();
			if(a.getIsFish()==isFish) {
			t.setUserName(client.getName());
			t.setUserSurname(client.getSurname());
			t.setUserImage(client.getImage());
			t.setIsFish(a.getIsFish());
			helper1.add(t);
			}
		}
		Collections.sort(helper1, Comparator.comparing(Trophy::getPublishDate).reversed());
        List<Trophy> helper = new ArrayList<>(helper1);
        
		List<Trophy> returner=new ArrayList<Trophy>();
		if(lastId==-1)
		{
			for(int i=0;i<helper.size()&& i<chunkSize;i++)
			{
				returner.add(helper.get(i));
			}
		}
		else if(helper.contains(new Trophy(lastId)))
		{
			int index=helper.indexOf(new Trophy(lastId));
			if(index+chunkSize<helper.size())
			{
				for(int i=index+1;i<=index+chunkSize;i++) {
					
					returner.add(helper.get(i));
				}
			}
			else
			{
				for(int i=index+1;i<helper.size();i++)
				{
					returner.add(helper.get(i));
				}
			}
		}
		else
		{
			int count=0;
			for(int i=0;i<helper.size();i++)
			{
				if(helper.get(i).getDate().isBefore(dateOfLastPost))
				{
					returner.add(helper.get(i));
					count++;
				}
				if(count==chunkSize)
					break;
			}
		}
        
		return checkClient(returner);
	}
	
	
	public Integer getPostSize(Boolean isFish,Integer idUser)
	{
		List<Trophy> returner=new ArrayList<Trophy>();
		if(idUser==-1)
		{
			
			List<Trophy> helper1=tr.findByIsPublic(true);
			
			for(Trophy t:helper1)
			{
				Animal a=rpAnimal.findById(t.getAnimal()).get();
				if(a.getIsFish()==isFish) {
				returner.add(t);
				}
			}
			return checkClient(returner).size();
		}
		
		List<Trophy> helper1=tr.findByIdClient(idUser);
		
		for(Trophy t:helper1)
		{
			Animal a=rpAnimal.findById(t.getAnimal()).get();
			if(a.getIsFish()==isFish) {
			returner.add(t);
			}
		}
		
		return checkClient(returner).size();
		
	}
	
	private List<Trophy> checkClient(List<Trophy> trophies)
	{
		List<Trophy> copy=new ArrayList<Trophy>();
		
		for(Trophy trophy:trophies)
		{
			RegisteredClient client=rpClient.findById(trophy.getIdClient()).get();
			if(client.getBlocked()==false && client.getDeleted()==false)
				copy.add(trophy);
		}
		return copy;
	}
	
	private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
		double earthRadius = 6371.0;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
	
	private boolean isPointInCircle(double centerLat, double centerLon, double radiusKm, double pointLat, double pointLon) {
        double distance = calculateDistance(centerLat, centerLon, pointLat, pointLon);
        return distance <= radiusKm;
    }
	
	public StatisticsModel getStatistics(String animal, Boolean dailyStatistics, Integer month, Double radius,
			Double latitude, Double longitude) {
		StatisticsModel sm = new StatisticsModel();
		List<Trophy> list = tr.findByAnimalAndIsPublic(animal,true);
		list=checkClient(list);
		LinkedHashMap<String, Integer> map;
		List<Trophy> listOfTrophies;
		if (latitude != null && longitude != null && radius != null)
		{
			List<Trophy> filteredList = new ArrayList<Trophy>();
			for (Trophy tr : list)
			{
				if (isPointInCircle(latitude, longitude, radius, tr.getLocationLat(), tr.getLocationLng()))
					filteredList.add(tr);
			}
			listOfTrophies = filteredList;
			
			
		}
		else
		{
			listOfTrophies = list;
		}
		
		if (dailyStatistics == true)
		{
			map = new LinkedHashMap<String, Integer>();
			map.put("00:00 - 04:00", 0);
			map.put("04:00 - 08:00", 0);
			map.put("08:00 - 12:00", 0);
			map.put("12:00 - 16:00", 0);
			map.put("16:00 - 20:00", 0);
			map.put("20:00 - 00:00", 0);

			for (Trophy tr : listOfTrophies) {
			    int hour = tr.getDate().getHour();
			 
			    if (hour >= 0 && hour < 4) {
			        map.put("00:00 - 04:00", map.getOrDefault("00:00 - 04:00", 0) + 1);
			    } else if (hour >= 4 && hour < 8) {
			        map.put("04:00 - 08:00", map.getOrDefault("04:00 - 08:00", 0) + 1);
			    } else if (hour >= 8 && hour < 12) {
			        map.put("08:00 - 12:00", map.getOrDefault("08:00 - 12:00", 0) + 1);
			    } else if (hour >= 12 && hour < 16) {
			        map.put("12:00 - 16:00", map.getOrDefault("12:00 - 16:00", 0) + 1);
			    } else if (hour >= 16 && hour < 20) {
			        map.put("16:00 - 20:00", map.getOrDefault("16:00 - 20:00", 0) + 1);
			    } else if (hour >= 20 && hour < 24) {
			        map.put("20:00 - 00:00", map.getOrDefault("20:00 - 00:00", 0) + 1);
			    }
			}
		}
		else 
		{	List<Trophy> filteredTrophies=new ArrayList<Trophy>();
			map = new LinkedHashMap<String, Integer>();
			for (Trophy tr : listOfTrophies)
			{
				if(tr.getDate().getMonthValue()==month)
				{
					filteredTrophies.add(tr);
				}
			}
			  map.put("1 - 3.", 0);
	          map.put("4 - 7.", 0);
	          map.put("8 - 11.", 0);
	          map.put("12 - 15.", 0);
	          map.put("16 - 19.", 0);
	          map.put("20 - 23.", 0);
	          map.put("24 - 27.", 0);
	          map.put("28 - 31.", 0);
			
			
			for(Trophy t:filteredTrophies)
			{
				int dayOfMonth=t.getDate().getDayOfMonth();
				int counter=0;
				for(Map.Entry<String, Integer> entry:map.entrySet())
				{
					if(counter==dayOfMonth/4) {
						map.put(entry.getKey(), entry.getValue()+1);
						break;
					}
					
					counter++;
				}
				
			}
			
			LinkedHashMap<String, Double> statsMap = new LinkedHashMap<String, Double>();
			for (Map.Entry<String, Integer> elem : map.entrySet())
			{
				if(filteredTrophies.size()!=0)
				statsMap.put(elem.getKey(), (elem.getValue() * 1.0) / filteredTrophies.size());
				else
					statsMap.put(elem.getKey(), 0.0);
			}
			sm.setStatistic(statsMap);
			
			return sm;
		}
		LinkedHashMap<String, Double> statsMap = new LinkedHashMap<String, Double>();
		for (Map.Entry<String, Integer> elem : map.entrySet())
		{
			if(listOfTrophies.size()!=0)
			statsMap.put(elem.getKey(), (elem.getValue() * 1.0) / listOfTrophies.size());
			else
				statsMap.put(elem.getKey(), 0.0);
		}
		sm.setStatistic(statsMap);
		
		return sm;
	}
}

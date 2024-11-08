package com.example.app.trophy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trophy")
public class TrophyController {
	private final TrophyService ts;
	
	@Autowired
	public TrophyController(TrophyService ts)
	{
		this.ts = ts;
	}
	
	
	@GetMapping("/all/public")
	public List<Trophy> getPublicTrophies()
	{
		return ts.getPublicTrophies();
	}
	
	
	@GetMapping("/my/all/hunting")
	public List<Trophy> getMyHuntingTrophies(@RequestParam Integer idClient,@RequestParam Integer lastId,@RequestParam Integer chunkSize,@RequestParam LocalDateTime dateOfLastPost)
	{
		 return ts.getMyHuntingOrFishingTrophies(idClient,false,lastId,chunkSize,dateOfLastPost);
	}
	@GetMapping("/my/all/fishing")
	public List<Trophy> getMyFishingTrophies(@RequestParam Integer idClient,@RequestParam Integer lastId,@RequestParam Integer chunkSize,@RequestParam LocalDateTime dateOfLastPost)
	{
		return ts.getMyHuntingOrFishingTrophies(idClient,true,lastId,chunkSize,dateOfLastPost);
	}
	
	
	
	@GetMapping("/all/public/hunting")
	public List<Trophy> getPublicHuntingTrophies(@RequestParam Integer lastId,@RequestParam Integer chunkSize,@RequestParam LocalDateTime dateOfLastPost)
	{
		 return ts.getPublicHuntingOrFishingTrophies(false,lastId,chunkSize,dateOfLastPost);
	}
	@GetMapping("/all/public/fishing")
	public List<Trophy> getPublicFishingTrophies(@RequestParam Integer lastId,@RequestParam Integer chunkSize,@RequestParam LocalDateTime dateOfLastPost)
	{
		
		return ts.getPublicHuntingOrFishingTrophies(true,lastId,chunkSize,dateOfLastPost);
	}
	
	@GetMapping("/post/size")
	public ResponseEntity<String> getPostSize(@RequestParam Integer idUser)
	{
		return new ResponseEntity<String>(ts.getPostSize(false, idUser)+" "+ts.getPostSize(true, idUser),HttpStatus.OK);
	}
	
	@GetMapping("/id")
	public ResponseEntity<Trophy> getTrophyById(@RequestParam Integer id)
	{
		return new ResponseEntity<Trophy>(ts.getTrophyById(id),HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<String> addTrophy(@RequestParam String name, @RequestParam LocalDateTime date, @RequestParam String description,
			@RequestPart byte[] image, @RequestParam Integer idClient, @RequestParam String animal, @RequestParam Boolean isPublic,
			@RequestParam(required=false) Integer idConfiguration,@RequestParam Double locationLat,Double locationLng)
	{
		ts.addTrophy(new Trophy(null, name, date, description, image, idClient, animal, isPublic,
				idConfiguration,locationLat,locationLng,LocalDateTime.now()));
		return new ResponseEntity<String>("Succesfully added a trophy!", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteTrophy(@RequestParam Integer id)
	{
		ts.deleteTrophy(id);
		return new ResponseEntity<String>("Successfully deleted a trophy!", HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestParam Integer id, @RequestParam String name, @RequestParam LocalDateTime date, @RequestParam String description,
			@RequestPart(required=false) byte[] image, @RequestParam Integer idClient, @RequestParam String animal, @RequestParam Boolean isPublic,
			 @RequestParam(required=false) Integer idConfiguration,@RequestParam Double locationLat,Double locationLng)
	{
		ts.update(new Trophy(id, name, date, description, image, idClient, animal, isPublic,
				idConfiguration,locationLat,locationLng,LocalDateTime.now()));
		return new ResponseEntity<String>("Successfully updated trophy!", HttpStatus.OK);
	}
	
	@GetMapping("/statistics")
	public ResponseEntity<StatisticsModel> getStatistics(@RequestParam String animal,@RequestParam Boolean dailyStatistics,
			@RequestParam(required=false) Integer month,
			@RequestParam(required=false) Double radius,
			@RequestParam(required=false) Double latitude,
			@RequestParam(required=false) Double longitude)
	{
		return new ResponseEntity<StatisticsModel>(ts.getStatistics(animal,dailyStatistics,month,radius,latitude,longitude),HttpStatus.OK);
	}
}

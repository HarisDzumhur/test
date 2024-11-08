package com.example.app.trophy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrophyRepository extends JpaRepository<Trophy, Integer> {

	public List<Trophy> findByIdClient(Integer idClient);
	List<Trophy> findByIdClientAndIsPublic(Integer idClient, Boolean isPublic);
	List<Trophy> findByIsPublic(Boolean isPublic);
	public List<Trophy> findByAnimal(String animal);
	public List<Trophy> findByAnimalAndIsPublic(String animal,Boolean isPublic);
}

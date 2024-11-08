package com.example.app.following;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowingRepository extends JpaRepository<Following, FollowingId>{

	public List<Following> findByidUser(Integer idUser);
	public List<Following> findByidGroup(Integer idGroup);
	public long deleteByidUser(Integer idUser);
	
}

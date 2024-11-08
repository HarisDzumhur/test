package com.example.app.fisherman;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishermanRepository extends JpaRepository<Fisherman, Integer>{
	public List<Fisherman> findByIdGroup(Integer idGroup);
}

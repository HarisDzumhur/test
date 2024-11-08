package com.example.app.hunter;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HunterRepository extends JpaRepository<Hunter, Integer>{

	public List<Hunter> findByidSection(Integer idSection);
	public List<Hunter> findByidGroup(Integer idGroup);
}

package com.example.app.section;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer>{
	public List<Section> findByIdGroup(Integer idGroup);
	public List<Section> findByName(String name);
}

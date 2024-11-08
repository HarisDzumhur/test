package com.example.app.marker.type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarkerTypeRepository extends JpaRepository<MarkerType, Integer>{

	public List<MarkerType> findByName(String name);
}

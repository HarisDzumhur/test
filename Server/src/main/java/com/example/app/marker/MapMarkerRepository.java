package com.example.app.marker;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapMarkerRepository extends JpaRepository<MapMarker, Integer>{

		public List<MapMarker> findByIsPublic(Boolean isPublic);
		public List<MapMarker> findByIdUser(Integer idUser);
}

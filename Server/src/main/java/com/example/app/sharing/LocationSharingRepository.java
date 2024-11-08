package com.example.app.sharing;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationSharingRepository extends JpaRepository<LocationSharing, LocationSharingId>{

	List<LocationSharing> findByidHunter1(Integer idHunter1);
	List<LocationSharing> findByidHunter2(Integer idHunter2);
}

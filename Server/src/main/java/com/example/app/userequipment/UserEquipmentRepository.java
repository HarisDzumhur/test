package com.example.app.userequipment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEquipmentRepository extends JpaRepository<UserEquipment, UserEquipmentId> {
	public List<UserEquipment> findByidConfiguration(Integer idConfiguration);
}

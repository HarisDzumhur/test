package com.example.app.equipment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
	public List<Equipment> findByName(String name);
	public List<Equipment> findBytypeId(Integer typeId);
}

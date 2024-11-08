package com.example.app.equipment.type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentTypeRepository extends JpaRepository<EquipmentType, Integer> {
	public List<EquipmentType> findByName(String name);
}

package com.example.app.animal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, String>{

	public List<Animal> findByisFish(Boolean isFish);
}

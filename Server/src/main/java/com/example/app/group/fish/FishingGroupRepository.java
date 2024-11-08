package com.example.app.group.fish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FishingGroupRepository extends JpaRepository<FishingGroup, Integer>{

}

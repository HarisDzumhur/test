package com.example.app.group.hunt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuntingGroupRepository extends JpaRepository<HuntingGroup, Integer> {

}

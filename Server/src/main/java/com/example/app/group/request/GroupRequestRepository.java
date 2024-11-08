package com.example.app.group.request;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRequestRepository extends JpaRepository<GroupRequest, Integer> {

	public List<GroupRequest> findByidGroup(Integer idGroup);
	public List<GroupRequest> findByidUser(Integer idUser);
	public long deleteByIdUser(Integer idUser);
}

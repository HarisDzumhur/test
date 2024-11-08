package com.example.app.notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationMessage, Integer> {
	public List<NotificationMessage> findByIdGroup(Integer idGroup);
	public List<NotificationMessage> findByIdClient(Integer idClient);
	public List<NotificationMessage> findByIdClientAndIsOld(Integer idClient, Boolean isOld);
	public long deleteByIdClient(Integer idClient);
}

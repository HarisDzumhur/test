package com.example.app.sharing.request;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.app.client.RegisteredClient;

@Repository
public interface SharingRequestRepository extends  JpaRepository<SharingRequest, SharingRequestId>{	
	List<SharingRequest> findByidUser1(Integer idUser1);
	List<SharingRequest> findByidUser2(Integer idUser2);
}
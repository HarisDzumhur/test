package com.example.app.client;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface RegisteredClientRepository extends JpaRepository<RegisteredClient, Integer> {
	
	public Optional<RegisteredClient> findBymail(String mail);
}

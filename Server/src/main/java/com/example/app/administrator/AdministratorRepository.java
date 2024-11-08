package com.example.app.administrator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, String>  {
	List<Administrator> findByEmail(String email);
}

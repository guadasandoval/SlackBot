package com.botti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.botti.model.Birthday;

@Repository
public interface BirthdayRepo extends JpaRepository<Birthday, Integer>{
	

}

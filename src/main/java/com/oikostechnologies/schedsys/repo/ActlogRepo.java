package com.oikostechnologies.schedsys.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oikostechnologies.schedsys.entity.ActivityLog;

public interface ActlogRepo extends JpaRepository<ActivityLog, Long> {

	
	List<ActivityLog> findAllByOrderByDateDesc();
	
}

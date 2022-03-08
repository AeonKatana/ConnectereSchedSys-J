package com.oikostechnologies.schedsys.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oikostechnologies.schedsys.entity.DailyTask;
import com.oikostechnologies.schedsys.entity.User;

public interface DailyTaskRepo extends JpaRepository<DailyTask, Long> {

	List<DailyTask> findAllByUser(User user);
	
}

package com.oikostechnologies.schedsys.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oikostechnologies.schedsys.entity.DailyTask;
import com.oikostechnologies.schedsys.entity.User;

public interface DailyTaskRepo extends JpaRepository<DailyTask, Long> {

	List<DailyTask> findAllByUserOrderByStarteddateDesc(User user);
	
	@Query("SELECT count(*) from DailyTask dt where dt.until = :today")
	long countDailyToday(@Param("today") LocalDate today);
    	
}

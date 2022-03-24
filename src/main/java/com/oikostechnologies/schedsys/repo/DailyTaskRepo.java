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
	
	@Query("SELECT count(*) from DailyTask dt where dt.starteddate = :today")
	long countDailyToday(@Param("today") LocalDate today);
	
	@Query("SELECT count(*) from DailyTask dt join dt.user u join u.company c where c.compname =:company and dt.starteddate =:today")
	long countDailyToday(@Param("today") LocalDate today, @Param("company") String company);
	
	@Query("SELECT count(*) from DailyTask dt join dt.user u where dt.starteddate = :today and u.id =:id")
	long countDailyToday(@Param("today") LocalDate today, @Param("id") long id);
	
}

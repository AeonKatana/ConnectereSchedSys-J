package com.oikostechnologies.schedsys.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oikostechnologies.schedsys.entity.DailyTask;
import com.oikostechnologies.schedsys.entity.User;
import com.oikostechnologies.schedsys.model.DailyTaskModel;
import com.oikostechnologies.schedsys.repo.DailyTaskRepo;
import com.oikostechnologies.schedsys.repo.UserRepo;

@Service
public class DailyTaskServiceImp implements DailyTaskService {
	
	@Autowired
	private DailyTaskRepo dailyrepo;

	@Autowired
	private UserRepo userrepo;
	
	@Override
	public DailyTaskModel addMyTask(DailyTaskModel model, User user) {
		
		
		
		DailyTask task = new DailyTask();
		task.setTitle(model.getTitle());
		task.setRecurring(model.isRecurring());
		task.setNote(model.getNote());
		if(!model.isRecurring()) {
			task.setUntil(LocalDate.ofInstant(Instant.now(), ZoneId.of("Asia/Manila")));
		}
		else {
			task.setUntil(LocalDate.parse(model.getUntil()));
		}
		task.setStarteddate(LocalDate.ofInstant(Instant.now(), ZoneId.of("Asia/Manila")));
		
		task.setDescription(model.getTaskdetail());
		task.setUser(user);
		
		dailyrepo.save(task);
		
		return model;

	}

	@Override
	public List<DailyTask> getMyTasksByUserId(long id) {
		
		User user = userrepo.findById(id).orElse(null);
		List<DailyTask> mytasks = dailyrepo.findAllByUser(user);
		
		for(DailyTask dt : mytasks) {
			System.out.println("My Task :" + dt.getTitle());
		}
		
		return mytasks;
	}

	@Override
	public long countCompleted() {
	
		return dailyrepo.count();
	}

}

package com.oikostechnologies.schedsys.service;

import java.util.List;

import com.oikostechnologies.schedsys.entity.DailyTask;
import com.oikostechnologies.schedsys.entity.User;
import com.oikostechnologies.schedsys.model.DailyTaskModel;

public interface DailyTaskService {

	public DailyTaskModel addMyTask(DailyTaskModel model, User user);
	public List<DailyTask>
	getMyTasksByUserId(long id);
	public long countCompleted();
	public long countDailyToday();
	
}

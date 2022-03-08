package com.oikostechnologies.schedsys.service;

import java.util.List;

import com.oikostechnologies.schedsys.entity.DailyTask;
import com.oikostechnologies.schedsys.model.DailyTaskModel;

public interface DailyTaskService {

	public DailyTaskModel addMyTask(DailyTaskModel model);
	public List<DailyTask>
	getMyTasksByUserId(long id);
	public long countCompleted();
	
}

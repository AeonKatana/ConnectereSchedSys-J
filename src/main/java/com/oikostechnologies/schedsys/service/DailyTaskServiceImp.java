package com.oikostechnologies.schedsys.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oikostechnologies.schedsys.entity.ActivityLog;
import com.oikostechnologies.schedsys.entity.DailyTask;
import com.oikostechnologies.schedsys.entity.NotifyUser;
import com.oikostechnologies.schedsys.entity.User;
import com.oikostechnologies.schedsys.model.DailyTaskModel;
import com.oikostechnologies.schedsys.model.PeopleModel;
import com.oikostechnologies.schedsys.repo.ActlogRepo;
import com.oikostechnologies.schedsys.repo.DailyTaskRepo;
import com.oikostechnologies.schedsys.repo.NotifyUserRepo;
import com.oikostechnologies.schedsys.repo.UserRepo;

@Service
public class DailyTaskServiceImp implements DailyTaskService {
	
	@Autowired
	private DailyTaskRepo dailyrepo;

	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private NotifyUserRepo notifrepo;
	
	@Autowired
	private ActlogRepo actrepo;
	
	@Override
	public DailyTaskModel addMyTask(DailyTaskModel model, User user) {
		
		
		
		DailyTask task = new DailyTask();
		task.setTitle(model.getTitle());
		task.setNote(model.getNote());
		task.setUntil(LocalDate.parse(model.getUntil()));
		task.setStarteddate(ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Manila")).toLocalDate());
		task.setDescription(model.getTaskdetail());
		task.setUser(user);
		dailyrepo.save(task);
		
		User sa = userrepo.findSuperAdmin();  // Add Superadmin to be notified on default
		NotifyUser superadmin = new NotifyUser();
		superadmin.setUserid(sa.getId());
		superadmin.setUsername(sa.fullname());
		superadmin.setDaily(task);
		notifrepo.save(superadmin);
		
		for(PeopleModel pm : model.getMentions()) { // Get all personnels that was mentioned and save them
			NotifyUser mention = new NotifyUser();
			mention.setUserid(pm.getId());
			mention.setUsername(pm.getName());
			mention.setDaily(task);
			notifrepo.save(mention);
		}
		
		
		ActivityLog compcreate = new ActivityLog(); // Create an activity log for this event
		compcreate.setAction("has created a daily task");
		compcreate.setTarget(task.getTitle());
		compcreate.setTargetlink("#");
		compcreate.setUser(user);
		compcreate.setDate(ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Manila")).toLocalDateTime());
		
		actrepo.save(compcreate);
		
		return model;

	}

	@Override
	public List<DailyTask> getMyTasksByUserId(long id) {
		
		User user = userrepo.findById(id).orElse(null);
		List<DailyTask> mytasks = dailyrepo.findAllByUserOrderByStarteddateDesc(user);
		
		for(DailyTask dt : mytasks) {
			System.out.println("My Task :" + dt.getTitle());
		}
		
		return mytasks;
	}

	@Override
	public long countCompleted() {
		return dailyrepo.count();
	}
	
	@Override
	public long countDailyToday() {		
		return dailyrepo.countDailyToday(ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Manila")).toLocalDate());
	}

	@Override
	public long countCompanyDaily(String company) {
		return dailyrepo.countDailyToday(ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Manila")).toLocalDate(), company);
	}

	@Override
	public long countMyDaily(long id) {
		return dailyrepo.countDailyToday(ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Asia/Manila")).toLocalDate(), id);
	}
	

}

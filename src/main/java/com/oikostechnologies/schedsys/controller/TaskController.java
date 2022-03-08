package com.oikostechnologies.schedsys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oikostechnologies.schedsys.entity.DailyTask;
import com.oikostechnologies.schedsys.model.DailyTaskModel;
import com.oikostechnologies.schedsys.service.DailyTaskService;

@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private DailyTaskService dailyservice;
	
	
	@GetMapping("/mytask/addtask")
	public String addMyTask(Model model) {
		return "addmytask";
	}
	
	@GetMapping("/assignedtask/addtask")
	public String addAssignedTask() {
		return "addpnltask";
	}
	
	@PostMapping("/savemytask")
	@ResponseBody
	public DailyTaskModel saveMyTask(@RequestBody DailyTaskModel daily) {
		
		DailyTaskModel task = dailyservice.addMyTask(daily);
		
		return task;
	}
	
	
}

package com.oikostechnologies.schedsys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oikostechnologies.schedsys.service.UserService;

@Controller
@RequestMapping("/personnel")
public class PersonnelController {

	@Autowired
	private UserService userservice;
	
	@GetMapping("/search")
	public String searchPersonnel(Model model , @RequestParam("search") String search) {
		model.addAttribute("masteradmins", userservice.searchUser(search));
		model.addAttribute("totalelement", userservice.searchUser(search).getTotalElements());
		model.addAttribute("totalpage", userservice.searchUser(search).getTotalPages());
		return "personnel";
	}
	
	
}

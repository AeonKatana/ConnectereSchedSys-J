package com.oikostechnologies.schedsys.controller;

import java.util.Arrays;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oikostechnologies.schedsys.entity.User;
import com.oikostechnologies.schedsys.service.UserService;

@Controller
@RequestMapping("/personnel")
public class PersonnelController {

	@Autowired
	private UserService userservice;
	
/**	
 * Manual Pagination and Search functionality
 *  
 * @GetMapping("/search")
	public String searchPersonnel(Model model , @RequestParam("search") String search) {
		model.addAttribute("parameter", search);
		model.addAttribute("masteradmins", userservice.searchUser(search));
		model.addAttribute("totalelement", userservice.searchUser(search).getTotalElements());
		model.addAttribute("totalpage", userservice.searchUser(search).getTotalPages());
		return "personnel";
	}
	
	@GetMapping("/page/{page}")
	public String personnelList(Model model, @PathVariable("page") int page) {
		model.addAttribute("currentPage", page);
		model.addAttribute("masteradmins", userservice.findAllUsers(page - 1));
		model.addAttribute("totalelement", userservice.findAllUsers(page - 1).getTotalElements());
		model.addAttribute("totalpage", userservice.findAllUsers(page - 1).getTotalPages());;
		
		return "personnel";
	}
	
	@GetMapping("/page/{page}/search")
	public String personnelList(Model model, @PathVariable("page") int page , @RequestParam("search") String search) {
		model.addAttribute("currentPage", page);
		model.addAttribute("parameter", search);
		model.addAttribute("masteradmins", userservice.findAllUsers(page - 1, search));
		model.addAttribute("totalelement", userservice.findAllUsers(page - 1, search).getTotalElements());
		model.addAttribute("totalpage", userservice.findAllUsers(page - 1, search).getTotalPages());;
		
		return "personnel";
	}
**/
	@GetMapping("/datatable")   // End point for DataTables in JQUERY AJAX
	@ResponseBody
	public DataTablesOutput<User> personnelList(@Valid DataTablesInput input, @RequestParam Map<String, String> queryParams){
		return userservice.findAll(input);
	}
	
}

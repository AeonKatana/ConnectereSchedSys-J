package com.oikostechnologies.schedsys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oikostechnologies.schedsys.model.CompanyModel;
import com.oikostechnologies.schedsys.model.UserModel;
import com.oikostechnologies.schedsys.service.CompanyService;

@Controller
@RequestMapping("/companies")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/addcompany")
	@ResponseBody
	public boolean addCompany(CompanyModel company, UserModel user) {
		companyService.addCompany(company, user);
		return true;
	}
	
	@GetMapping("/page/{page}")
	public String companylist(Model model, @PathVariable("page") int page) {
		model.addAttribute("masteradmincomp", companyService.getCompanies());
		model.addAttribute("totalelement", companyService.getCompanies().getTotalElements());
		model.addAttribute("totalpage", companyService.getCompanies().getTotalPages());
		return "companyprofile";
	}
	
	@GetMapping("/search")
	public String companylist(Model model, @RequestParam("search") String search) {
		model.addAttribute("masteradmincomp", companyService.searchCompany(search));
		model.addAttribute("totalelement", companyService.searchCompany(search).getTotalElements());
		model.addAttribute("totalpage", companyService.searchCompany(search).getTotalPages());
		return "companyprofile";
	}
	
	
}

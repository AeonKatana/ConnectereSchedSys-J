package com.oikostechnologies.schedsys.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	
	@RequestMapping("/login-page")
	public String loginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "loginform";
        }
		return "redirect:/";
	}
	
	@RequestMapping("/perform-login")
	public String performLogin() {
		return "redirect:/";
	}
	
	@RequestMapping("/perform-logout")
	public String performLogout() {
		return "";
	}
}

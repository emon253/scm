package com.scm.scm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.domain.User;
import com.scm.scm.repository.UserRepository;


@RequestMapping("/user")
@Controller
public class UserDashboardController {

	@Autowired
	private UserRepository repository;
	
	@ModelAttribute
	public void commonData(Model model, Principal principal) {
		User user = repository.getUserByEmail(principal.getName());
		model.addAttribute("user", user);
	}
	
	@RequestMapping("/dashboard")
	public String dashBoard(Model model) {
		model.addAttribute("title", "Dashboard");
		return "General/user_dashboard";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package com.scm.scm.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.repository.UserRepository;

@Controller
public class HomeController {
	@Autowired
	private UserRepository repository;

	@RequestMapping("/home")
	public String homeView(Model model, Principal principal) {
		if (principal != null)
			model.addAttribute("user", repository.getUserByEmail(principal.getName()));
		return "home";
	}
	@RequestMapping("/")
	public String homeView2(Model model, Principal principal) {
		if (principal != null)
			model.addAttribute("user", repository.getUserByEmail(principal.getName()));
		return "home";
	}

	@RequestMapping("/test")
	public String testView() {
		return "test";
	}
}

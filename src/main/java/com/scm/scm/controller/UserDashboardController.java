package com.scm.scm.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.scm.domain.Contact;


@RequestMapping("/user")
@Controller
public class UserDashboardController {

//	@Autowired
//	private UserRepository repository;
	
	@RequestMapping("/dashboard")
	public String dashBoard(Model model) {
		model.addAttribute("title", "Dashboard");
		return "General/user_dashboard";
	}
	@RequestMapping("/add-contact")
	public String contactFormView(Model model) {
		model.addAttribute("title", "Add Contact");
		return "General/add_contact";
	}
	
	@ModelAttribute
	public void commonData(Model model, Principal principal) {
//		User user = repository.getUserByEmail(principal.getName());
//		System.out.println(user);
	}
	@PostMapping("/process-form")
	public String processAddContact(@ModelAttribute Contact contact) {
		System.out.println(contact);
		return "General/add_contact";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

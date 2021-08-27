package com.scm.scm.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.scm.scm.domain.User;
import com.scm.scm.repository.UserRepository;
import com.scm.scm.service.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignupController {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@RequestMapping("/signup")
	public String signupView(Model model) {
		model.addAttribute("title", "Signup");
		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, HttpSession session) {

		try {
			if (!agreement) {
				throw new Exception("You have to accept terms and conditions..");
			}

			if (result.hasErrors()) {
				System.out.println(result);
				model.addAttribute(user);
				return "signup";
			}

			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			model.addAttribute("user", new User());
			repository.save(user);
			session.setAttribute("message", new Message("You've Successfully registered", "alert-success"));
			return "signup";

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			session.setAttribute("message", new Message("Somethig went wrong!! ", "alert-danger"));
			return "signup";
		}

	}
}

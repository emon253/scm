package com.scm.scm.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.scm.scm.domain.Contact;
import com.scm.scm.domain.User;
import com.scm.scm.repository.UserRepository;
import com.scm.scm.service.Message;

@RequestMapping("/user")
@Controller
public class ContactController {
	@Autowired
	private UserRepository repository;

	@Autowired
	private ContactRepository contactRepository;

	@RequestMapping("/add-contact")
	public String contactFormView(Model model, Principal principal) {
		model.addAttribute("title", "Add Contact");
		User user = repository.getUserByEmail(principal.getName());
		model.addAttribute("user", user);

		return "General/add_contact";
	}
	


	@PostMapping("/process-contact-form")
	public String processAddContact(@ModelAttribute("contact") Contact contact,
			@RequestParam("profileImage") MultipartFile file, Principal principal, HttpSession session, Model model) {
		try {
			var user = repository.getUserByEmail(principal.getName());
			model.addAttribute("user", user);

			/// file process
			if (file.isEmpty()) {
				contact.setImage("default2.jpg");

			} else {
				contact.setImage(file.getOriginalFilename());
				var saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
				Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			}

			contact.setUser(user);
			user.getContacts().add(contact);
			repository.save(user);

			session.setAttribute("message", new Message("Contact Added", "alert-success"));

		} catch (Exception e) {
			session.setAttribute("message", new Message("Something went wrong!! Try again", "alert-danger"));

			e.printStackTrace();
		}
		return "General/add_contact";

	}

	@RequestMapping("/view-contact/{page}")
	public String viewContacts(@PathVariable("page") int page, Model model, Principal principal) {
		model.addAttribute("title", "Contacts");

		User user = repository.getUserByEmail(principal.getName());
		var pageable = PageRequest.of(page, 5);
		
		Page<Contact> contacts = contactRepository.findContactsByUser(user.getId(),pageable);
//		contacts.forEach(c -> System.out.println(c.getImage()));
		model.addAttribute("contacts", contacts);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPages", contacts.getTotalPages());
		model.addAttribute("user", user);

		return "General/view_contacts";
	}

	@RequestMapping("/delete/{cid}")
	public RedirectView deteletContact(Model model, Principal principal, @PathVariable("cid") int cid) {
		RedirectView rView = new RedirectView();
		var contact = contactRepository.findById(cid).get();
		contact.setUser(null);
		contactRepository.delete(contact);
		rView.setUrl("/user/view-contact/0");
		return rView;
	}

}

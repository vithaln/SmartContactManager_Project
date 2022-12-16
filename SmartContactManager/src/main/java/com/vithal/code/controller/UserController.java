package com.vithal.code.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vithal.code.entities.Contact;
import com.vithal.code.entities.User;
import com.vithal.code.repository.UserRepo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepo repo;
	
	//addding common data for all handlers
	@ModelAttribute
	public void addCommondate(Model model,Principal principal) {
		//get the user by using userName(email)
		String userName = principal.getName();
		System.out.println("USER ==> "+userName);
		
		User user = repo.getUserByUserName(userName);
		System.out.println("USER DETAILS ARE "+user);
		
		//send data to dashboard
		
		model.addAttribute("user",user);
	}
	
	//Dashboard home
	@GetMapping("/index")
	public String dashBoard(Model model,Principal principal) {
	
		model.addAttribute("title","userDashboard");
		return "normal/user_dashboard";
	}
	@GetMapping("/add_contact")
	public String openAddContacts(Model model) {
		model.addAttribute("title","Add contacts");
		model.addAttribute("contact",new Contact());
		return"normal/add_contact";
	}
	
	//processing add contact form
	
	@PostMapping("/process_contact")
	public String processContact(@ModelAttribute Contact contact ) {
		
		
		System.out.println("DATA FOR CONTACT "+contact);
		return "/normal/add_contact";
	}
	
}

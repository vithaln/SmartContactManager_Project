package com.vithal.code.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vithal.code.entities.User;
import com.vithal.code.repository.UserRepo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepo repo;
	@GetMapping("/index")
	public String dashBoard(Model model,Principal principal) {
		
		
		//get the user by using userName(email)
		
		String userName = principal.getName();
		System.out.println("USER ==> "+userName);
		
		User user = repo.getUserByUserName(userName);
		System.out.println("USER DETAILS ARE "+user);
		
		//send data to dashboard
		
		model.addAttribute("user",user);
		return "normal/user_dashboard";
	}
}

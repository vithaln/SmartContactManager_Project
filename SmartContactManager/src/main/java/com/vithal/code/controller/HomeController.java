package com.vithal.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vithal.code.entities.User;
import com.vithal.code.helper.Message;
import com.vithal.code.repository.UserRepo;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {

	
	@Autowired private UserRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	/*
	 * 
	 * 
	 * @GetMapping("/t")
	 * 
	 * @ResponseBody public String testing() { User user = new User();
	 * 
	 * user.setName("Vithal nivargi"); user.setEmail("fhgh@gmail.com");
	 * 
	 * Contact contact = new Contact(); user.getContacts().add(contact);
	 * repo.save(user); return "testing went fine, it's working fine "; }
	 */
	
	@GetMapping("/")
	public String home(Model model) {
		
		model.addAttribute("title","Home-Smart Contact Manager");
		return "home";
	}
	
	//home
	@GetMapping("/home")
	public String homes(Model model) {
		
		model.addAttribute("title","Home-Smart Contact Manager");
		return "home";
	}
	
	//about page
	@GetMapping("/about")
	public String about(Model model) {
		
		model.addAttribute("title","About-Smart Contact Manager");
		return "about";
	}
	
	//signup page
		@GetMapping("/signup")
		public String signup(Model model) {
			
			model.addAttribute("title","Register-Smart Contact Manager");
			model.addAttribute("user",new User());
			return "signup";
		}
		
		
		
		//handler for the  registering user details
		@PostMapping("/do_register")
		public String registeringUser(@Valid @ModelAttribute("user") User user, @RequestParam(value = "agreement",defaultValue = "false") boolean agreement,Model model,BindingResult results, HttpSession session) {
			try {
				if(!agreement) {
					System.out.println("You haven't agreed terms and contions!");
					throw new Exception("You haven't agreed terms and contions!");
				}
				
				if(results.hasErrors()) {
					
					model.addAttribute("user",user);
					System.out.println("ERROR "+results.toString());
					return "signup";
				}
				user.setEnabled(true);
				user.setRole("ROLE_USER");
				user.setImageURL("default.png");
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
				System.out.println("Agreement "+agreement);
				System.out.println("USER "+user);
				User result = repo.save(user);
				model.addAttribute("user",new User());
				
				session.setAttribute("message", new Message("YOU HAVE BEEN SUCCESSFULLY REGISTERED!","alert-success"));
				return "signup";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("user",user);
				session.setAttribute("message", new Message("something went wrong!"+e.getMessage(),"alert-danger"));
				return "signup";
			
				
				
			}
			
		
			
					}
		
		//handler for custom login
		@GetMapping("/signin")
		public String signin(Model model) {
			
			model.addAttribute("title","Login page!!");
			
			return "login";
		}
		
		//handler for failure login
				 @GetMapping("/login_fail")
				public String loginFailed(Model model) {
					
					model.addAttribute("title","Login failed page!!");
					
					return "login_fail";
				}
		
				 //success message
				
					@PostMapping("/success")
					public String success(Model model) {
						
						model.addAttribute("title","Success page!!");
						
						return "success";
					}
}

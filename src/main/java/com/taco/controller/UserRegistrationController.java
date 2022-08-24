package com.taco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taco.model.RegistrationForm;
import com.taco.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 @GetMapping
	 public String registerForm() {
	 return "register";
	 }
	 
	 @PostMapping
	 public String processRegistration(RegistrationForm form) {
		 userRepo.save(form.toUser(passwordEncoder));
		 return "redirect:/login";
	 }
	
}

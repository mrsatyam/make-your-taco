package com.taco.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.taco.model.Order;
import com.taco.model.Taco;
import com.taco.model.Users;
import com.taco.repository.OrderRepository;
import com.taco.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private UserRepository userRepo;

	@GetMapping(value = "/current")
	public String orderForm(Model model) {
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal Users user) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		order.setUser(user);
		orderRepo.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}

//	@PostMapping
//	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, Authentication principal) {
//		if (errors.hasErrors()) {
//			return "orderForm";
//		}
//		Users user = (Users) principal.getPrincipal();
//		order.setUser(user);
//		orderRepo.save(order);
//		sessionStatus.setComplete();
//		return "redirect:/";
//	}
	
//	@PostMapping
//	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, Principal principal) {
//		if (errors.hasErrors()) {
//			return "orderForm";
//		}
//		Users user = userRepo.findByUsername(principal.getName());
//		order.setUser(user);
//		orderRepo.save(order);
//		sessionStatus.setComplete();
//		return "redirect:/";
//	}
	
//	@PostMapping
//	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
//		if (errors.hasErrors()) {
//			return "orderForm";
//		}
//		Authentication authentication =
//				 SecurityContextHolder.getContext().getAuthentication();
//				Users user = (Users) authentication.getPrincipal();
//		order.setUser(user);
//		orderRepo.save(order);
//		sessionStatus.setComplete();
//		return "redirect:/";
//	}
	
	

}

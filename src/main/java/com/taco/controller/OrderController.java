package com.taco.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.taco.model.Order;
import com.taco.model.Taco;
import com.taco.repository.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderRepository orderRepo;

	@GetMapping(value = "/current")
	public String orderForm(Model model) {
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, Model model) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		orderRepo.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}

}

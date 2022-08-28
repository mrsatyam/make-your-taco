package com.taco.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.taco.model.Ingredient;
import com.taco.model.Ingredient.Type;
import com.taco.model.Order;
import com.taco.model.Taco;
import com.taco.repository.IngredientRepository;
import com.taco.repository.TacoRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@Slf4j
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	private static final String DESIGN = "design";

	private final IngredientRepository ingredientRepo;

	// end::injectingIngredientRepository[]
	private TacoRepository tacoRepo;
	
	// end::injectingDesignRepository[]
	/*
	 * //tag::injectingIngredientRepository[] public
	 * DesignTacoController(IngredientRepository ingredientRepo) {
	 * this.ingredientRepo = ingredientRepo; }
	 * //end::injectingIngredientRepository[]
	 */
	// tag::injectingDesignRepository[]

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
		this.ingredientRepo = ingredientRepo;
		this.tacoRepo = designRepo;
	}

	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(ingredients::add);
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		model.addAttribute(DESIGN, new Taco());
		return DESIGN;
	}

	@PostMapping
	public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {

		if (errors.hasErrors()) {
			return DESIGN;
		}
		Taco saved = tacoRepo.save(taco);
		order.addDesign(saved);
		log.info("Processing design: " + taco);
		return "redirect:/orders/current";

	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(i -> i.getType().equals(type)).toList();
	}
}

package com.taco.repository;

import org.springframework.stereotype.Service;

import com.taco.model.Ingredient;

@Service
public interface IngredientRepository {
	
	Iterable<Ingredient> findAll();

	Ingredient findOne(String id);

	Ingredient save(Ingredient ingredient);
}

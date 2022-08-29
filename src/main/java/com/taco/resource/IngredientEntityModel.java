package com.taco.resource;

import org.springframework.hateoas.RepresentationModel;

import com.taco.model.Ingredient;
import com.taco.model.Ingredient.Type;

import lombok.Getter;

public class IngredientEntityModel extends RepresentationModel<IngredientEntityModel> {
	
	@Getter
	private String name;
	@Getter
	private Type type;

	public IngredientEntityModel(Ingredient ingredient) {
		this.name = ingredient.getName();
		this.type = ingredient.getType();
	}
}

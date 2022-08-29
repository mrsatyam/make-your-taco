package com.taco.resource;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.taco.controller.RestDesignTacoController;
import com.taco.model.Ingredient;

public class IngredientEntityModelAssembler
		extends RepresentationModelAssemblerSupport<Ingredient, IngredientEntityModel> {

	public IngredientEntityModelAssembler() {
		super(RestDesignTacoController.class, IngredientEntityModel.class);
	}

	@Override
	public IngredientEntityModel toModel(Ingredient ingredient) {
		return createModelWithId(ingredient.getId(), ingredient);
	}

	@Override
	protected IngredientEntityModel instantiateModel(Ingredient ingredient) {
		return new IngredientEntityModel(ingredient);
	}

}

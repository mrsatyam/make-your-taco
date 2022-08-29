package com.taco.resource;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.taco.model.Ingredient;
import com.taco.model.Taco;

import lombok.Getter;

@Relation(value = "taco", collectionRelation = "tacos")
public class TacoEntityModel extends RepresentationModel<TacoEntityModel> {
	private static final IngredientEntityModelAssembler ingredientAssembler = new IngredientEntityModelAssembler();

	@Getter
	private final String name;
	@Getter
	private final Date createdAt;
	@Getter
	private final CollectionModel<IngredientEntityModel> ingredients;

	public TacoEntityModel(Taco taco) {
		this.name = taco.getName();
		this.createdAt = taco.getCreatedAt();
		this.ingredients = ingredientAssembler.toCollectionModel(taco.getIngredients());
	}

}

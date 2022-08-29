package com.taco.resource;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import com.taco.controller.RestDesignTacoController;
import com.taco.model.Taco;

public class TacoEntityModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoEntityModel> {

	public TacoEntityModelAssembler() {
		super(RestDesignTacoController.class, TacoEntityModel.class);
	}

	@Override
	public TacoEntityModel toModel(Taco entity) {
		return createModelWithId(entity.getId(), entity);
	}

	@Override
	protected TacoEntityModel instantiateModel(Taco taco) {
		return new TacoEntityModel(taco);
	}

}

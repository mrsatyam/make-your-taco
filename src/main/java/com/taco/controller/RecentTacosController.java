package com.taco.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.taco.model.Taco;
import com.taco.repository.TacoRepository;
import com.taco.resource.TacoEntityModel;
import com.taco.resource.TacoEntityModelAssembler;

@RepositoryRestController
public class RecentTacosController {
	private TacoRepository tacoRepo;

	public RecentTacosController(TacoRepository tacoRepo) {
		this.tacoRepo = tacoRepo;
	}

	@GetMapping(path = "/tacos/recent", produces = "application/hal+json")
	public ResponseEntity<CollectionModel<TacoEntityModel>> recentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		CollectionModel<TacoEntityModel> tacoResources = new TacoEntityModelAssembler().toCollectionModel(tacos);
		CollectionModel<TacoEntityModel> recentResources = CollectionModel.of(tacoResources);

		recentResources.add(linkTo(methodOn(RecentTacosController.class).recentTacos()).withRel("recents"));
		return new ResponseEntity<>(recentResources, HttpStatus.OK);
	}

	/**
	 * RepresentationModelProcessor, an interface for manipulating resources before
	 * they’re returned through the API. For your purposes, you need an
	 * implementation of RepresentationModelProcessor that adds a recents link to
	 * any resource of type PagedModels<EntityModel<Taco>> (the type returned for
	 * the /api/tacos endpoint)
	 **/
	@Bean
	public RepresentationModelProcessor<PagedModel<EntityModel<Taco>>> tacoProcessor(EntityLinks links) {
		return new RepresentationModelProcessor<PagedModel<EntityModel<Taco>>>() {
			@Override
			public PagedModel<EntityModel<Taco>> process(PagedModel<EntityModel<Taco>> resource) {
				resource.add(links.linkFor(Taco.class).slash("recent").withRel("recents"));
				return resource;
			}
		};
	}
}

package com.taco.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.taco.model.Order;
import com.taco.model.Taco;
import com.taco.repository.OrderRepository;
import com.taco.repository.TacoRepository;
import com.taco.resource.TacoEntityModel;
import com.taco.resource.TacoEntityModelAssembler;

@RestController
@RequestMapping(path = "/rest/design", produces = "application/json") // produces tells that this controller will only
																		// handle
																		// the requests if the request's ACCEPT header
																		// includes "application/json"
@CrossOrigin(origins = "*") // @CrossOrigin allows clients from any domain to consume the API
public class RestDesignTacoController {

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}

	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}

	@Autowired
	private TacoRepository tacoRepo;
	@Autowired
	private OrderRepository orderRepo;

	@GetMapping("/recent")
	public CollectionModel<TacoEntityModel> recentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
		List<Taco> tacos = tacoRepo.findAll(page).getContent();
		CollectionModel<TacoEntityModel> recentResources = new TacoEntityModelAssembler().toCollectionModel(tacos);
		recentResources.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(RestDesignTacoController.class).recentTacos())
				.withRel("recents"));
		return recentResources;

	}

	@GetMapping("{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
		Optional<Taco> optTaco = tacoRepo.findById(id);
		if (optTaco.isPresent()) {
			return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepo.save(taco);
	}

	@PatchMapping("/{orderId}")
	public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order updatedOrder) {

		Order existingOrder = null;
		Optional<Order> orderOp = orderRepo.findById(orderId);

		if (orderOp.isPresent()) {
			existingOrder = orderOp.get();
			if (updatedOrder.getDeliveryName() != null) {
				existingOrder.setDeliveryName(updatedOrder.getDeliveryName());
			}
			if (updatedOrder.getDeliveryStreet() != null) {
				existingOrder.setDeliveryStreet(updatedOrder.getDeliveryStreet());
			}
			if (updatedOrder.getDeliveryCity() != null) {
				existingOrder.setDeliveryCity(updatedOrder.getDeliveryCity());
			}
			if (updatedOrder.getDeliveryState() != null) {
				existingOrder.setDeliveryState(updatedOrder.getDeliveryState());
			}
			if (updatedOrder.getDeliveryZip() != null) {
				existingOrder.setDeliveryZip(updatedOrder.getDeliveryState());
			}
			if (updatedOrder.getCcNumber() != null) {
				existingOrder.setCcNumber(updatedOrder.getCcNumber());
			}
			if (updatedOrder.getCcExpiration() != null) {
				existingOrder.setCcExpiration(updatedOrder.getCcExpiration());
			}
			if (updatedOrder.getCcCVV() != null) {
				existingOrder.setCcCVV(updatedOrder.getCcCVV());
			}

		}
		return orderRepo.save(existingOrder);
	}

	@PutMapping("/{orderId}")
	public ResponseEntity<Order> putOrder(@RequestBody Order order) {
		MultiValueMap<String , String> headers = new LinkedMultiValueMap<>();
		headers.add("name", "saty");
		ResponseEntity<Order> res = new ResponseEntity<>(orderRepo.save(order), headers, HttpStatus.FOUND);
		return res;
	}

	@DeleteMapping("/{orderId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable("orderId") Long orderId) {
		try {
			orderRepo.deleteById(orderId);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}

package com.taco.repository;

import org.springframework.data.repository.CrudRepository;

import com.taco.model.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}

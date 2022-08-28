package com.taco.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.taco.model.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}

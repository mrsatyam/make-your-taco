package com.taco.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taco.model.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
	 Users findByUsername(String username);
}

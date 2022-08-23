package com.taco.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.taco.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	 User findByUsername(String username);
}

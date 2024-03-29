package com.taco.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.taco.model.Order;
import com.taco.model.Users;

public interface OrderRepository extends CrudRepository<Order, Long>{
	List<Order> findByDeliveryZip(String deliveryZip);
	@Query("from Order o where o.deliveryCity='Seattle'")
	List<Order> readOrdersDeliveredInSeattle();
	List<Order> findByUserOrderByPlacedAtDesc(Users user, Pageable pageable);
}


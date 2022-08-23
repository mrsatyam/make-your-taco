package com.taco.repository;

import com.taco.model.Order;

public interface OrderRepository {
	Order save(Order order);
}

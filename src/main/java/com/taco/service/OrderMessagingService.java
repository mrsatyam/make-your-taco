package com.taco.service;

import com.taco.model.Order;

public interface OrderMessagingService {
	void sendOrder(Order order);
}

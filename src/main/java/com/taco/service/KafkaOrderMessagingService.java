package com.taco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.taco.model.Order;

public class KafkaOrderMessagingService implements OrderMessagingService {
	
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;

	@Override
	public void sendOrder(Order order) {
		kafkaTemplate.sendDefault(order);
	}

}

package com.taco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import com.taco.model.Order;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderMessagingService implements OrderMessagingService {
	
	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;

	@Override
	public void sendOrder(Order order) {
		kafkaTemplate.send("tacocloud.orders.topic", order);
	}

}

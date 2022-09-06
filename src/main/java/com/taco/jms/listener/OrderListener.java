package com.taco.jms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taco.kitchen.KitchenUI;
import com.taco.model.Order;

@Component
public class OrderListener {
	@Autowired
	KitchenUI kitchenUI;

	// @JmsListener(destination = "tacocloud.order.queue")
	@RabbitListener(queues = "tacocloud.order.queue")
	public void receiveOrder(Order order) {
		kitchenUI.displayOrder(order);
	}

}

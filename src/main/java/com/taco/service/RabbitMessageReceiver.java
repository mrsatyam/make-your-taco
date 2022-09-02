package com.taco.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.taco.model.Order;

@Component
public class RabbitMessageReceiver {
	@Autowired
	private RabbitTemplate rabbit;
	private MessageConverter converter;

	public Order receiveOrder() {
		/*
		 * Message message = rabbit.receive("tacocloud.order.queue", 30000); // this
		 * timeout can also be cofigured with //
		 * spring.rabbitmq.template.receive-timeout=30000 return message != null ?
		 * (Order) converter.fromMessage(message) : null; }
		 */
		// replace above to convert Message to Order automatically
		return (Order) rabbit.receiveAndConvert("tacocloud.order.queue", 30000);// this timeout can also be configured with
																				// spring.rabbitmq.template.receive-timeout=30000

	}
}

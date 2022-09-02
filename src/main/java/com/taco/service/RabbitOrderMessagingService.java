package com.taco.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taco.model.Order;

@Service
public class RabbitOrderMessagingService implements OrderMessagingService {
	@Autowired
	RabbitTemplate rabbit;

	@Override
	public void sendOrder(Order order) {
		/*
		 * MessageConverter converter = rabbit.getMessageConverter(); MessageProperties
		 * props = new MessageProperties(); Message message = converter.toMessage(order,
		 * props); rabbit.send("tacocloud.order", message);
		 */
		// replace above by
		rabbit.convertAndSend("tacocloud.order", order, this::setMessageProperties);
	}

	private Message setMessageProperties(Message message) {
		MessageProperties props = message.getMessageProperties();
		props.setHeader("X_ORDER_SOURCE", "WEB");
		return message;
	}

}

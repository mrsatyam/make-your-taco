package com.taco.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.taco.model.Order;

public class JmsOrderMessagingService implements OrderMessagingService {

	@Autowired
	private JmsTemplate jms;

	@Autowired
	Destination orderQueue;

	@Override
	public void sendOrder(Order order) {
		/*
		 * jms.send(orderQueue, session -> session .createObjectMessage(order));
		 * session.createObjectMessage(order) will create the message and send it to the
		 * default destination set in application.properties
		 */
		jms.convertAndSend("tacocloud.order.queue", order, this::addOrderSource/*MessagePostProcessor object*/); // convert and send does not take a
																					// MessageCreator it automatically
																					// converts the object to message
																					// and send it to the Destination

	}

	private Message addOrderSource(Message message) throws JMSException { //
		message.setStringProperty("X_ORDER_SOURCE", "WEB");
		return message;
	}

}

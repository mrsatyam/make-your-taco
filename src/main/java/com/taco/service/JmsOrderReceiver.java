package com.taco.service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

import com.taco.model.Order;

public class JmsOrderReceiver implements OrderReceiver {

	@Autowired
	JmsTemplate jms;
	@Autowired
	MessageConverter converter;

	@Autowired
	Destination orderQueue;

//	@Override
//	public Order receiveOrder() throws JMSException {
//		Message message = jms.receive(orderQueue);
//		return (Order) converter.fromMessage(message);
//	}

	@Override
	public Order receiveOrder() {
		return (Order) jms.receiveAndConvert(orderQueue);
	}
}

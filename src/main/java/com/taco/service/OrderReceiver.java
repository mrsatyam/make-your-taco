package com.taco.service;

import javax.jms.JMSException;

import com.taco.model.Order;

public interface OrderReceiver {
	Order receiveOrder() throws JMSException;
}

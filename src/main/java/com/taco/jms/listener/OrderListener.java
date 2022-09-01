package com.taco.jms.listener;

import java.lang.constant.DirectMethodHandleDesc.Kind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.taco.kitchen.KitchenUI;
import com.taco.model.Order;

@Component
public class OrderListener {
	@Autowired
	KitchenUI kitchenUI;

	@JmsListener(destination = "tacocloud.order.queue")
	public void receiveOrder(Order order) {
		kitchenUI.displayOrder(order);
	}

}

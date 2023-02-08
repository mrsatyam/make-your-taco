package com.taco.integration.handlers;

import com.taco.integration.properties.ApiProperties;
import com.taco.model.Order;
import com.taco.model.Taco;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class OrderSubmitMessageHandler
        implements GenericHandler<Order> {

    private RestTemplate rest;
    private ApiProperties apiProps;
    public OrderSubmitMessageHandler(
            ApiProperties apiProps, RestTemplate rest) {
        this.apiProps = apiProps;
        this.rest = rest;
    }


    @Override
    public Object handle(Order order, MessageHeaders headers) {
        rest.postForObject(apiProps.getUrl(), order, String.class);
        return null;
    }
}

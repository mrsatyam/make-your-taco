package com.taco.integration.transformers;

import com.taco.model.Order;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.mail.Message;
@Component
public class EmailToOrderTransformer
        extends AbstractMailMessageTransformer<Order> {
    @Override
    protected AbstractIntegrationMessageBuilder<Order>
    doTransform(Message mailMessage) throws Exception {
        Order tacoOrder = processPayload(mailMessage);
        return MessageBuilder.withPayload(tacoOrder);
    }

}
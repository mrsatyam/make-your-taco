package com.taco;

import com.taco.service.JmsOrderMessagingService;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.jms.Destination;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("tacocloud.order.queue");
    }

    /*
     * @Bean public MappingJackson2MessageConverter messageConverter() { // now
     * MappingJackson2MessageConverter will be treated // as the Default message
     * converter. MappingJackson2MessageConverter messageConverter = new
     * MappingJackson2MessageConverter();
     * messageConverter.setTypeIdPropertyName("_typeId");// This is very important,
     * as it enables the receiver to know // what type to convert an incoming
     * message to, and _type_Id // property contains the type of object(by default
     * the class // name) Map<String, Class<?>> typeIdMappings = new HashMap<>();
     * typeIdMappings.put("order", Order.class);
     * messageConverter.setTypeIdMappings(typeIdMappings); // maps a synthetic order
     * type ID to the Order class // Instead of the fully qualified classname being
     * sent in the message’s // _typeId(line 32) property,the value order will be
     * sent.
     */

    /**
     * At the receiving application, a similar message converter will have been
     * configured, mapping order to its own understanding of what an order is.
     **//*
     *
     * return messageConverter; }
     */
    @Bean
    public JmsOrderMessagingService messagingService() {
        return new JmsOrderMessagingService();
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}

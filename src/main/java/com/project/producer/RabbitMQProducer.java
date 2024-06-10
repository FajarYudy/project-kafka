package com.project.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author fajaryudi
 * @created 2024/06/10 - 11.00
 */
@Component
public class RabbitMQProducer {
    private final String exchangeName = "notification";
    private final String routingKey = "all_notif";
    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}

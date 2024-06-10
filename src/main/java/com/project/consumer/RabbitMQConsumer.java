package com.project.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author fajaryudi
 * @created 2024/06/10 - 11.03
 */
@Component
public class RabbitMQConsumer {
    private final String queueName = "all_notif";
    @RabbitListener(queues = queueName)
    public void receiveMessage(String message)
    {
        // Handle the received message here
        System.out.println("Received message: " + message);
    }
}

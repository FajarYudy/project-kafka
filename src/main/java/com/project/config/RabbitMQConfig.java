package com.project.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fajaryudi
 * @created 2024/06/10 - 11.04
 */
@Configuration
public class RabbitMQConfig {
    private final String queueName = "all_notif";
    private final String exchangeName = "notification";
    private final String routingKey = "all_notif";

    @Bean
    public Queue queue(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-queue-type", "quorum");
        return new Queue(queueName, true, false, false, args);
//        return new Queue(queueName, true);
    }

    @Bean
    public Exchange exchange(){
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Binding binding(Queue queue, Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routingKey). noargs();
    }
}

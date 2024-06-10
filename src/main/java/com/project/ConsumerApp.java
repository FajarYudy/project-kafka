package com.project;

import com.rabbitmq.client.*;

/**
 * @author fajaryudi
 * @created 2024/06/07 - 13.12
 */
public class ConsumerApp {
    public static void main(String[] args) {
        try {
            System.out.println("Simple ConsumerApp RabbitListener");
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri("amqp://guest:guest@localhost:5672/");
            factory.setVirtualHost("/");

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                System.out.print("ConsumerApp : ");
                System.out.print(message.getEnvelope().getRoutingKey() +" ");
                System.out.println(new String(message.getBody()));
            };

            CancelCallback cancelCallback =  consumerTag -> {
                System.out.println("Consumer is canceled");
            };

//            channel.basicConsume("all_notif", deliverCallback, cancelCallback);
            channel.basicConsume("all_notif", true, deliverCallback, cancelCallback);

//            channel.close();
//            connection.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}

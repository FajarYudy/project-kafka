package com.project;

import com.rabbitmq.client.*;

import java.util.Map;

/**
 * @author fajaryudi
 * @created 2024/06/07 - 13.12
 */
public class ProducerApp {
    public static void main(String[] args) {
        try {
            System.out.println("Simple ProducerApp basicPublish");
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUri("amqp://guest:guest@localhost:5672/");
            factory.setVirtualHost("/");

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();

            for (int i=0; i<10; i++){
                String msg = "tes kirim dari java "+ i;
                AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().
                        headers(Map.of("sample", "value")).
                        build();
                channel.basicPublish("events", "all_notif", properties, msg.getBytes());
                System.out.println("ProducerApp : "+msg);
            }

            channel.close();
            connection.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}

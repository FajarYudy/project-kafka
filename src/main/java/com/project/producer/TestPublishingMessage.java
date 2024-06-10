package com.project.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

/**
 * @author fajaryudi
 * @created 2024/06/06 - 10.45
 */
public class TestPublishingMessage {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private final String topicName = "test1";

    public void sendMessage(String msg){
        kafkaTemplate.send(topicName, msg);
    }

    public void sendMessageFuture(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }
}

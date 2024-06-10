package com.project.producer;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author fajaryudi
 * @created 2024/06/06 - 11.28
 */
@RestController
@RequestMapping("/test")
public class TestPublishingMessageController {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.topic-name}")
    private String topicName;

    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @GetMapping
    public String testPublishingMessage(@RequestParam String msg){
        kafkaTemplate.send(topicName, msg);
        return "Sukses : "+ msg;
    }

    @GetMapping("/all-topic")
    public Set<String> getPublishingMessage() throws ExecutionException, InterruptedException {
        Map<String, Object> props = new HashMap<>();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);

        AdminClient adminClient = AdminClient.create(props);

        ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
        listTopicsOptions.listInternal(true);

        Set<String> msg = adminClient.listTopics(listTopicsOptions).names().get();
        System.out.println("topics:" + adminClient.listTopics(listTopicsOptions).names().get());

        return msg;
    }
}

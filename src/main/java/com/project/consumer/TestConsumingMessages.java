package com.project.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author fajaryudi
 * @created 2024/06/06 - 11.04
 */
@Component
public class TestConsumingMessages {
    private final String topicName = "test1";
    private final String groupId = "java";

    @KafkaListener(topics = topicName, groupId = groupId)
    public void listenGroup1(String message){
        System.out.println("Received Message in group java : " + message);
    }

//    @KafkaListener(topics = "topicName")
//    public void listenWithHeaders(
//            @Payload String message,
//            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
//        System.out.println(
//                "Received Message: " + message +"from partition: " + partition);
//    }
//
//    @KafkaListener(
//            topicPartitions = @TopicPartition(topic = topicName,
//                    partitionOffsets = {
//                            @PartitionOffset(partition = "0", initialOffset = "0"),
//                            @PartitionOffset(partition = "3", initialOffset = "0")}),
//            containerFactory = "partitionsKafkaListenerContainerFactory")
//    public void listenToPartition(
//            @Payload String message,
//            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
//        System.out.println(
//                "Received Message: " + message + "from partition: " + partition);
//    }
}

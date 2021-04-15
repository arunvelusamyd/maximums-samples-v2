package com.flexy.kafka.partition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class PartitionedMessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${topic.name.partitioned}")
    private String partitionedTopicName;

    public void sendMessageToPartition(String message, int partition) {
        kafkaTemplate.send(partitionedTopicName, partition, null, message);
    }

}

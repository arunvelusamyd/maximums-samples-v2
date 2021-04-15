package com.flexy.kafka.partition;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class PartitionedMessageListener {

    private CountDownLatch partitionLatch = new CountDownLatch(2);

    public void setPartitionLatch(int timeOut, TimeUnit timeUnit) throws InterruptedException {
        this.partitionLatch.await(timeOut, timeUnit);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${topic.name.partitioned}", partitions = { "6", "4" , "3"}), containerFactory = "partitionsKafkaListenerContainerFactory")
    public void listenToPartition(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Message: " + message + " from partition: " + partition);
        this.partitionLatch.countDown();
    }

}

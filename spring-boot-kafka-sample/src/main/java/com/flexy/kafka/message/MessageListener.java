package com.flexy.kafka.message;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MessageListener {

    private CountDownLatch latch = new CountDownLatch(3);

    public void setLatch(int timeOut, TimeUnit timeUnit) throws InterruptedException {
        this.latch.await(timeOut, timeUnit);
    }

    @KafkaListener(topics = "${topic.name.message}", groupId = "ashok", containerFactory = "ashokKafkaListenerContainerFactory")
    public void listenGroupAshok1(String message) {
        System.out.println(" #################### Ashok's first listener ####################");
        System.out.println("Received Message in group 'ashok': " + message);
        latch.countDown();
    }

    @KafkaListener(topics = "${topic.name.message}", groupId = "ashok", containerFactory = "ashokKafkaListenerContainerFactory")
    public void listenGroupAshok2(String message) {
        System.out.println(" #################### Ashok's second listener ####################");
        System.out.println("Received Message in group 'ashok': " + message);
        latch.countDown();
    }

    @KafkaListener(topics = "${topic.name.message}", groupId = "Sukumar", containerFactory = "sukumarKafkaListenerContainerFactory")
    public void listenGroupSukumar(String message) {
        System.out.println("Received Message in group 'Sukumar': " + message);
        latch.countDown();
    }

    @KafkaListener(topics = "${topic.name.message}", containerFactory = "headersKafkaListenerContainerFactory")
    public void listenWithHeaders(@Payload String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Message: " + message + " from partition: " + partition);
        latch.countDown();
    }

}

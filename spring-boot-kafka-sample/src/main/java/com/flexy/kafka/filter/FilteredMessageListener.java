package com.flexy.kafka.filter;

import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FilteredMessageListener {

    private CountDownLatch filterLatch = new CountDownLatch(2);

    public void setFilterLatch(int timeOut, TimeUnit timeUnit) throws InterruptedException {
        this.filterLatch.await(timeOut, timeUnit);
    }

    @KafkaListener(topics = "${topic.name.filtered}", containerFactory = "filterKafkaListenerContainerFactory")
    public void listenWithFilter(String message) {
        System.out.println("Received Message in filtered listener: " + message);
        this.filterLatch.countDown();
    }

}

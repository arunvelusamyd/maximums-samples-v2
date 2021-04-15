package com.flexy.kafka.jsonstore;

import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ProductMessageListener {

    private CountDownLatch productLatch = new CountDownLatch(1);

    @KafkaListener(topics = "${topic.name.product}", containerFactory = "productKafkaListenerContainerFactory")
    public void productListener(Product product) {
        System.out.println("Received product info: " + product);
        this.productLatch.countDown();
    }

    public void setProductLatch(int timeOut, TimeUnit timeUnit) throws InterruptedException {
        this.productLatch.await(timeOut, timeUnit);
    }

}

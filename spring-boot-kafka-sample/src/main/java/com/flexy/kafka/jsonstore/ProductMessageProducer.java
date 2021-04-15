package com.flexy.kafka.jsonstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

public class ProductMessageProducer {

    @Value(value = "${topic.name.product}")
    private String productTopicName;

    @Autowired
    private KafkaTemplate<String, Product> productKafkaTemplate;

    public void sendGreetingMessage(Product product) {
        productKafkaTemplate.send(productTopicName, product);
    }

}

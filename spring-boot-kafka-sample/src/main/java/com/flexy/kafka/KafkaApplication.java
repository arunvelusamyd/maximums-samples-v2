package com.flexy.kafka;

import com.flexy.kafka.filter.FilteredMessageListener;
import com.flexy.kafka.filter.FilteredMessageProducer;
import com.flexy.kafka.jsonstore.Product;
import com.flexy.kafka.jsonstore.ProductMessageListener;
import com.flexy.kafka.jsonstore.ProductMessageProducer;
import com.flexy.kafka.message.MessageListener;
import com.flexy.kafka.message.MessageProducer;
import com.flexy.kafka.partition.PartitionedMessageListener;
import com.flexy.kafka.partition.PartitionedMessageProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);

        MessageProducer messageProducer = context.getBean(MessageProducer.class);
        messageProducer.sendMessage("Hello, World!");
        MessageListener messageListener = context.getBean(MessageListener.class);
        messageListener.setLatch(10, TimeUnit.SECONDS);


        /*PartitionedMessageProducer partitionedMessageProducer = context.getBean(PartitionedMessageProducer.class);
        for (int i = 0; i < 8; i++) {
            partitionedMessageProducer.sendMessageToPartition("Hello To Partitioned Topic!", i);
        }
        PartitionedMessageListener partitionedMessageListener = context.getBean(PartitionedMessageListener.class);
        partitionedMessageListener.setPartitionLatch(10, TimeUnit.SECONDS);

        FilteredMessageProducer filteredMessageProducer = context.getBean(com.flexy.kafka.filter.FilteredMessageProducer.class);
        filteredMessageProducer.sendMessageToFiltered("Hello Kafka!");
        filteredMessageProducer.sendMessageToFiltered("Hello World!");
        FilteredMessageListener filteredMessageListener = context.getBean(com.flexy.kafka.filter.FilteredMessageListener.class);
        filteredMessageListener.setFilterLatch(10, TimeUnit.SECONDS);*/


        /*ProductMessageProducer productMessageProducer = context.getBean(ProductMessageProducer.class);
        productMessageProducer.sendGreetingMessage(new Product("iPhone", "5", "Smart phone from Apple"));
        ProductMessageListener productMessageListener = context.getBean(ProductMessageListener.class);
        productMessageListener.setProductLatch(10, TimeUnit.SECONDS);*/

        context.close();
    }

}

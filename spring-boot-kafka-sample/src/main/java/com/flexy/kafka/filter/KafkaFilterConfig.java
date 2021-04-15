package com.flexy.kafka.filter;

import com.flexy.kafka.jsonstore.ProductMessageProducer;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

//@EnableKafka
//@Configuration
public class KafkaFilterConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${topic.name.filtered}")
    private String filteredTopicName;

    public ConsumerFactory<String, String> consumerFactory(String groupId) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(String groupId) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(groupId));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> filterKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = kafkaListenerContainerFactory("filter");
        factory.setRecordFilterStrategy(record -> !record.value()
                .contains("Kafka"));
        return factory;
    }

    @Bean
    public NewTopic topic3() {
        return new NewTopic(filteredTopicName, 1, (short) 1);
    }

    @Bean
    public FilteredMessageProducer filteredMessageProducer() {
        return new FilteredMessageProducer();
    }

    @Bean
    public FilteredMessageListener filteredMessageListener() {
        return new FilteredMessageListener();
    }

}

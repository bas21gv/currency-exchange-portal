package com.maveric.thinknxt.kafka.config;

import com.maveric.thinknxt.kafka.model.Order;
import com.maveric.thinknxt.kafka.serdes.JsonSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {

    public static final String CUSTOMER_TOPIC = "customer-topic";
    public static final String DOMAIN_TOPIC = "domain-topic";
    @Autowired
    private KafkaProperties kafkaProperties;

//    @Bean
//    public NewTopic topicBuilder() {
//        return TopicBuilder.name("orders").build();
//    }

    @Bean
    public JsonSerializer jsonSerializer() {
        return new JsonSerializer();
    }

    @Bean
    public ProducerFactory<String, Order> producerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties(null), new StringSerializer(), jsonSerializer());
    }

    @Bean
    public KafkaTemplate<String, Order> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

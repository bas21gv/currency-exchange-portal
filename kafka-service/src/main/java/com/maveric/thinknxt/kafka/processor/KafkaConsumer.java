package com.maveric.thinknxt.kafka.processor;

import com.maveric.thinknxt.kafka.model.Customer;
import com.maveric.thinknxt.kafka.model.Domain;
import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class KafkaConsumer {

//    @KafkaListener(topics = KafkaConfig.CUSTOMER_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(Customer customer) {
        log.info("Message received :: {}", customer);
    }

//    @KafkaListener(topics = EventStreamProcessor.DOMAIN_ACTIVE, groupId = "${spring.kafka.consumer.group-id}")
    public void consumeDomain(Domain domain) {
        log.info("Domain comsumed :: {} status {}", domain.getDomain(), domain.isDead());
    }
}

package com.maveric.thinknxt.kafka.processor;

import com.maveric.thinknxt.kafka.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, Order> kafkaTemplate;

    public void orderMessage(Order order) {
        Message<Order> message = MessageBuilder
                .withPayload(order)
                .setHeader(KafkaHeaders.TOPIC, "orders")
                .build();
        kafkaTemplate.send(message);
    }
/*
    public void sendMessage(Customer customer) {
        Message<Customer> message = MessageBuilder
                .withPayload(customer)
                .setHeader(KafkaHeaders.TOPIC, KafkaConfig.CUSTOMER_TOPIC)
                .build();
        kafkaTemplate.send(message);
    }

    public void sendMessage(String domainKeyword) {
        Flux<DomainList> response = WebClient.create()
                .get()
                .uri("https://api.domainsdb.info/v1/domains/search?domain="+domainKeyword+"&zone=com")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(DomainList.class);

        response.subscribe(domainList -> domainList.getDomains().forEach(
                domain -> kafkaTemplate.send(KafkaConfig.DOMAIN_TOPIC, domain)
        ));
    }*/
}

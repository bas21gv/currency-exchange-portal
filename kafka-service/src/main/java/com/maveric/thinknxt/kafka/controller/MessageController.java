package com.maveric.thinknxt.kafka.controller;

import com.maveric.thinknxt.kafka.model.Customer;
import com.maveric.thinknxt.kafka.model.Order;
import com.maveric.thinknxt.kafka.processor.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class MessageController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/stock")
    public ResponseEntity<String> orderMessage(@RequestBody Order order) {
        log.info("Message received {}", order);
        kafkaProducer.orderMessage(order);
        return ResponseEntity.ok("Message sent successfully!");
    }

   /* @PostMapping("/api/v1/message")
    public ResponseEntity<String> sendMessage(@RequestBody Customer customer) {
        log.info("Message received {}", customer);
        kafkaProducer.sendMessage(customer);
        return ResponseEntity.ok("Message sent successfully!");
    }

    @GetMapping("/lookup/{domainKeyword}")
    public ResponseEntity<String> lookup(@PathVariable String domainKeyword) {
        kafkaProducer.sendMessage(domainKeyword);
        return ResponseEntity.ok("Domain sent");
    }*/
}

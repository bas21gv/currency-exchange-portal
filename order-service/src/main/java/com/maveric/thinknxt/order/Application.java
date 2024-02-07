package com.maveric.thinknxt.order;

import com.maveric.thinknxt.order.model.Order;
import com.maveric.thinknxt.order.model.OrderType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Supplier;

@SpringBootApplication
@Slf4j
public class Application {

    private static long orderId = 0;
    LinkedList<Order> buyOrders = new LinkedList<>(List.of(
            new Order(++orderId, 1, 1, 100, LocalDateTime.now(), OrderType.BUY, 1000),
            new Order(++orderId, 2, 1, 200, LocalDateTime.now(), OrderType.BUY, 1050),
            new Order(++orderId, 3, 1, 100, LocalDateTime.now(), OrderType.BUY, 1030),
            new Order(++orderId, 4, 1, 200, LocalDateTime.now(), OrderType.BUY, 1050),
            new Order(++orderId, 5, 1, 200, LocalDateTime.now(), OrderType.BUY, 1000),
            new Order(++orderId, 11, 1, 100, LocalDateTime.now(), OrderType.BUY, 1050)
    ));

    LinkedList<Order> sellOrders = new LinkedList<>(List.of(
            new Order(++orderId, 6, 1, 200, LocalDateTime.now(), OrderType.SELL, 950),
            new Order(++orderId, 7, 1, 100, LocalDateTime.now(), OrderType.SELL, 1000),
            new Order(++orderId, 8, 1, 100, LocalDateTime.now(), OrderType.SELL, 1050),
            new Order(++orderId, 9, 1, 300, LocalDateTime.now(), OrderType.SELL, 1000),
            new Order(++orderId, 10, 1, 200, LocalDateTime.now(), OrderType.SELL, 1020)
    ));

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Supplier<Message<Order>> orderBuySupplier() {
        return () -> {
            if (buyOrders.peek() != null) {
                Message<Order> message = MessageBuilder
                        .withPayload(buyOrders.peek())
                        .setHeader(KafkaHeaders.KEY, Objects.requireNonNull(buyOrders.poll()).getId())
                        .build();
                log.info("Buy Order : {}", message.getPayload());
                return message;
            } else
                return null;
        };
    }

    @Bean
    public Supplier<Message<Order>> orderSellSupplier() {
        return () -> {
            if (sellOrders.peek() != null) {
                Message<Order> message = MessageBuilder
                        .withPayload(sellOrders.peek())
                        .setHeader(KafkaHeaders.KEY, Objects.requireNonNull(sellOrders.poll()).getId())
                        .build();
                log.info("Sell Order : {}", message.getPayload());
                return message;
            } else
                return null;
        };
    }
}

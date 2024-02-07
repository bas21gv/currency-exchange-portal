package com.maveric.thinknxt.stock;

import com.maveric.thinknxt.stock.model.Order;
import com.maveric.thinknxt.stock.model.Transaction;
import com.maveric.thinknxt.stock.serdes.OrderSerdes;
import com.maveric.thinknxt.stock.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.StreamJoined;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

@RequiredArgsConstructor
@SpringBootApplication
@Slf4j
public class Application {

    private final OrderService orderService;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }


    @Bean
    public Serde<Order> orderSerde() {
        return new OrderSerdes();
    }
    @Bean
    public BiConsumer<KStream<Long, Order>, KStream<Long, Order>> stockOrders() {
        return (orderBuy, orderSell) -> orderBuy.merge(orderSell)
                .peek((k, v) ->{
                    log.info("New({}) {}",k,v);
                    orderService.addOrder(v);
                });
    }
}

package com.maveric.thinknxt.stock.serdes;

import com.maveric.thinknxt.stock.model.Order;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class OrderSerdes implements Serde<Order> {
    @Override
    public Serializer<Order> serializer() {
        return new JsonSerializer<>();
    }

    @Override
    public Deserializer<Order> deserializer() {
        return new JsonDeserializer<>(Order.class);
    }

}

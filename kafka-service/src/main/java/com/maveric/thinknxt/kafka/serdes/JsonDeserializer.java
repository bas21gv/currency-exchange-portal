package com.maveric.thinknxt.kafka.serdes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maveric.thinknxt.kafka.model.Domain;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class JsonDeserializer<Order> implements Deserializer<Order> {

    private Class<Order> destinationClass;

    public JsonDeserializer(Class<Order> destinationClass) {
        this.destinationClass = destinationClass;
    }

    @Override
    public Order deserialize(String topic, byte[] bytes) {
        if (bytes == null)
            return null;

        try {
            return JsonMapper.readFromJson(new String(bytes, StandardCharsets.UTF_8), destinationClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

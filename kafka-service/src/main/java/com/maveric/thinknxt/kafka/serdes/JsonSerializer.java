package com.maveric.thinknxt.kafka.serdes;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

public class JsonSerializer<Order> implements Serializer<Order> {

    @Override
    public byte[] serialize(String topic, Order data) {
        if (data == null)
            return null;

        try {
            return JsonMapper.writeToJson(data).getBytes(StandardCharsets.UTF_8);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}

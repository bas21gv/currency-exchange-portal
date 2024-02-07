package com.maveric.thinknxt.kafka.serdes;

import com.maveric.thinknxt.kafka.model.Domain;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public final class DomainSerdes extends Serdes.WrapperSerde<Domain>{

    public DomainSerdes() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(Domain.class));
    }
    public static Serde<Domain> serdes() {
        return Serdes.serdeFrom(new JsonSerializer<>(), new JsonDeserializer<>(Domain.class));
    }
}

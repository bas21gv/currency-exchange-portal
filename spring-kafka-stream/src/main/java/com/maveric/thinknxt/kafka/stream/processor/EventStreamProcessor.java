package com.maveric.thinknxt.kafka.stream.processor;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventStreamProcessor {

    private static final Logger log = LoggerFactory.getLogger(EventStreamProcessor.class);
    public static final String INPUT_TOPIC = "spring.kafka.stream.input";
    public static final String OUTPUT_TOPIC = "spring.kafka.stream.output";
    @Autowired
    private StreamsBuilder streamsBuilder;

    @PostConstruct
    public void streamTopology() {
        KStream<String, String> kStream = streamsBuilder.stream(INPUT_TOPIC,
                Consumed.with(Serdes.String(), Serdes.String()));
        kStream.filter((key, value) -> value.startsWith("msg_"))
                .mapValues((k, v) -> v.substring(v.indexOf('_')+1))
                .peek((k, v) -> log.info("Key: {} Value: {}",k,v))
                .to(OUTPUT_TOPIC, Produced.with(Serdes.String(), Serdes.String()));
    }
}

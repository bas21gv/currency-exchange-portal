package com.maveric.thinknxt.kafka.stream.config;

import com.maveric.thinknxt.kafka.stream.processor.EventStreamProcessor;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@EnableKafkaStreams
@Configuration
public class KafkaStreamConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        KafkaAdmin kafkaAdmin = new KafkaAdmin(kafkaProperties.buildAdminProperties());
        kafkaAdmin.setFatalIfBrokerNotAvailable(kafkaProperties.getAdmin().isFailFast());
        return kafkaAdmin;
    }
    @Bean
    public StreamsConfig streamsConfig() {
        return new StreamsConfig(kafkaProperties.buildStreamsProperties());
    }
    @Bean
    public NewTopic topicBuilder() {
        return TopicBuilder.name(EventStreamProcessor.INPUT_TOPIC)
                .partitions(2)
                .replicas(1)
                .build();
    }
}

package com.hiberus.config;

import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaStreamsConfig {

    @Value("${spring.kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.schema.registry.url}")
    private String schemaRegistryUrl;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public Properties kafkaStreamsProperties() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, groupId);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, org.apache.kafka.common.serialization.Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde.class);
        props.put("schema.registry.url", schemaRegistryUrl);
        return props;
    }
}

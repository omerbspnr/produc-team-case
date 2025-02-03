package com.kafein.product.userservice.config.kafka.configuration;

import com.kafein.product.userservice.dto.UserCreatePayloadDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.host}")
    private String kafkaHost;

    @Bean
    public KafkaTemplate<String, UserCreatePayloadDto> producerFactory()
    {


        return new KafkaTemplate<>(getUserDtoProducerFactory());
    }

    private ProducerFactory<String, UserCreatePayloadDto> getUserDtoProducerFactory()
    {
        Map<String, Object> configProms = new HashMap<>();

        configProms.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
        configProms.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProms.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProms);
    }


}

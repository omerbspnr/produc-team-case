package com.kafein.product.userservice.service;

import com.kafein.product.userservice.dto.UserCreatePayloadDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service

public class UserCreatedKafkaProduceService {
    private static final Logger logger = LoggerFactory.getLogger(UserCreatedKafkaProduceService.class);
    private final KafkaTemplate<String, UserCreatePayloadDto> kafkaTemplate;

    public UserCreatedKafkaProduceService(KafkaTemplate<String, UserCreatePayloadDto> kafkaTemplate)
    {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(UserCreatePayloadDto userCreatePayloadDto)
    {

        CompletableFuture<SendResult<String, UserCreatePayloadDto>> future = kafkaTemplate.send("user_created.0", userCreatePayloadDto);

        future.thenApply( result -> {


            logger.info("Message : {}, published, topic : {}, partition: {} and offset : {}",
                    userCreatePayloadDto.toString(),
                    result.getRecordMetadata().topic(),
                    result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset()
                    );
            return String.format("%d-%d", result.getRecordMetadata().partition(),result.getRecordMetadata().offset());
        }).exceptionally(err -> {logger.info("error occured", err); return null;});

        try {
            future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

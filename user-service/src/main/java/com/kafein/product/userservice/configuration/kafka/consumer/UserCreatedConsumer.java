package com.kafein.product.userservice.configuration.kafka.consumer;

import com.kafein.product.userservice.dto.UserCreatePayloadDto;
import com.kafein.product.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedConsumer {
    private static Logger logger = LoggerFactory.getLogger(UserCreatedConsumer.class);

    private final UserService userService;

    public UserCreatedConsumer(UserService userService)
    {
        this.userService = userService;
    }

    @KafkaListener(topics = "user_created.0",
            groupId = "user-service-user-created-consumer",
            containerFactory = "concurrentKafkaListenerContainerFactory"
    )
    public void consumeCreatedUser(@Payload UserCreatePayloadDto userCreatePayloadDto) {
        logger.info("Consumed created user {}", userCreatePayloadDto);
        try {
            userService.createUser(userCreatePayloadDto);

        } catch (Exception e) {
            logger.info("exception occured while saving user {}", userCreatePayloadDto);
        }
    }
}

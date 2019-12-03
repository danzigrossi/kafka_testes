package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "users";

    @Autowired
    RetryTemplate retryTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplateBi;


    public void sendMessage(String message) {


        logger.info(String.format("#### -> Producing message -> %s", message));
        try {
            kafkaTemplateBi.send(TOPIC, message);
        } catch (Throwable e) {
            logger.info(String.format("#### -> Error"));
        }
        logger.info(String.format("#### -> Leave"));
    }
}
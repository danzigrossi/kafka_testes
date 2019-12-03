package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

//    @KafkaListener(topics = "<topic1>", groupId = "<consumer1>")
//    public void consumeDetail(String message) throws IOException {
//        logger.info(String.format("#### -> MESSAGE1 -> %s", message));
//    }
//
//    @KafkaListener(topics = "<topic2>", groupId = "<consumer2>")
//    public void consumeErrors(String message) throws IOException {
//        logger.info(String.format("==== -> MESSAGE2 -> %s", message));
//    }
}
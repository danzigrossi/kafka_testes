package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


@Component
public class RetryFunction {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    @Autowired
    RetryTemplate retryTemplate;

    public void sendMessage(String message) {
        AtomicInteger index = new AtomicInteger();
        AtomicReference<String> text = new AtomicReference<>();
        retryTemplate.execute(context -> {
            index.getAndIncrement();
            text.set("a");
            return true;
//            throw new RuntimeException("Failed operation");
        }, context -> {
            text.set("b");
            return true;
        });

        System.out.println(text + " - " + index);
        logger.info(String.format("#### -> Producing message -> %s", message));
//        kafkaTemplate.send(TOPIC, message);
    }
}

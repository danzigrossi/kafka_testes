package com.example.demo;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;


// fonte
//https://dzone.com/articles/kafka-producer-and-consumer-example
public class ProducerClient {
    public static String KAFKA_BROKERS = "localhost:9092";
    public static Integer MESSAGE_COUNT = 2;
    public static String CLIENT_ID = "client1";
    public static String TOPIC_NAME = "demo";

    static void runProducer(String message) {
        Producer<Long, String> producer = createProducer();
        for (int index = 0; index < MESSAGE_COUNT; index++) {
            ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(TOPIC_NAME,
                    "This is record " + index);
            try {
                RecordMetadata metadata = producer.send(record).get();
                System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
                        + " with offset " + metadata.offset());
            } catch (ExecutionException e) {
                System.out.println("Error in sending record 3");
                System.out.println(e);
            } catch (InterruptedException e) {
                System.out.println("Error in sending record 2");
                System.out.println(e);
            }
            System.out.println("finishing tryies");

        }
        System.out.println("finish everything");
    }

    public static Producer<Long, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<Long, String>(props);
    }
}

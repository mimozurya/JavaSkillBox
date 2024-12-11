package org.example.skillbox_mod6_orderservice;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OrderService {
    @Autowired
    private KafkaTemplate<String, OrderRequest> kafkaTemplate;

    public void send(OrderRequest request) {
        kafkaTemplate.send("order-topic",request);
    }

    @KafkaListener(topics = {"order-status-topic"})
    public void listenStatus(@Payload String message,
                             @Header(KafkaHeaders.RECEIVED_PARTITION) String partition,
                             @Header(KafkaHeaders.KEY) String key,
                             @Header(KafkaHeaders.TOPIC) String topic,
                             @Header(KafkaHeaders.TIMESTAMP) String timestamp){
        log.info("Received message: {}", message);
        log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}", key, partition, topic, timestamp);
    }
}

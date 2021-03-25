package io.apicurio.example;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import io.apicurio.example.schema.avro.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class Consumer {

    @KafkaListener(topics = ApicurioKafkaDemoApp.EVENTS_TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Event message) {
        log.info("Consumer consumed message {} from topic {}", message, ApicurioKafkaDemoApp.EVENTS_TOPIC);
    }
}

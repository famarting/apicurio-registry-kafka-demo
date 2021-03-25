package io.apicurio.example;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import io.apicurio.example.schema.avro.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    public void send(Event payload) {
        log.info("Producer sending message {} to topic {}", payload, ApicurioKafkaDemoApp.EVENTS_TOPIC);
        this.kafkaTemplate.send(ApicurioKafkaDemoApp.EVENTS_TOPIC, payload);
    }
}
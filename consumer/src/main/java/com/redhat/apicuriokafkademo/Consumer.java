package com.redhat.apicuriokafkademo;

import com.redhat.apicuriokafkademo.schema.avro.Event;
import com.redhat.apicuriokafkademo.utils.Topic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Consumer {

    @KafkaListener(topics = Topic.NAME, groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Event message) {
        log.info("Consumer consumed message {} from topic {}", message, Topic.NAME);
    }
}

package io.apicurio.example;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import io.apicurio.example.schema.avro.Event;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class Consumer {

    @Incoming("events-sink")
    public void consume(Event message) {
        log.info("Consumer consumed message {} from topic {}", message, "events");
    }
}

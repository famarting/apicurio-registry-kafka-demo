package io.apicurio.example;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import io.apicurio.example.schema.avro.Event;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class Producer {

    @Inject
    @Channel("events")
    Emitter<Event> eventsEmitter;

    public void send(Event payload) {
        log.info("Producer sending message {} to events channel", payload);
        this.eventsEmitter.send(payload);
    }
}
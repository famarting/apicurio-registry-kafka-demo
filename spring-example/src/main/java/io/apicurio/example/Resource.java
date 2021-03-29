package io.apicurio.example;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.apicurio.example.schema.avro.Event;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/kafka")
public class Resource {

    private final Producer producer;

    @PostMapping(value = "/publish")
    public void publish(@RequestBody InputEvent event) {
        log.info("REST Controller has received entity: {}", event);
        Event avroEvent = new Event();
        avroEvent.setName(event.getName());
        avroEvent.setDescription(event.getDescription());
        avroEvent.setSource("spring");
        this.producer.send(avroEvent);
    }

}
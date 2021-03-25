package io.apicurio.example;

import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import io.apicurio.example.schema.avro.Event;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/kafka")
public class Resource {

    @Inject
    Producer producer;

    @POST
    @Path("/publish")
    public void publish(InputEvent event) {
        log.info("REST Controller has received entity: {}", event);
        Event avroEvent = new Event();
        avroEvent.setCreatedOn(new Date().getTime());
        avroEvent.setName(event.getName());
        avroEvent.setDescription(event.getDescription());
        this.producer.send(avroEvent);
    }
}
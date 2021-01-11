# spring-boot-kafka-apicurio

This projects show example of how to integrate apicurio with kafka 


1. setup kafka and a topic "events"
2. extract server certificate and import it into a java key store as trust store. Configure application.yaml
3. install service registry and import the event avro schema
    ```mvn clean install -DskipTests -Pupload```
    or use the API
    ```
    curl --location --request POST 'http://apicurioregistry1.streams-playground-1.lab.redhat.com/api/artifacts' \
    --header 'Content-Type: application/json; artifactType=AVRO' \
    --header 'X-Registry-ArtifactId: events-value' \
    --data-raw '{
      "name": "Event",
      "namespace": "com.redhat.apicuriokafkademo.schema.avro",
      "type": "record",
      "doc": "Avro Schema for Event",
      "fields" : [ {
        "name" : "name",
        "type" : "string"
      }, {
        "name" : "description",
        "type" : "string"
      }, {
        "name" : "createdOn",
        "type" : "int",
        "logicalType": "timestamp-millis"
      }
      ]
    }'   
    ```

4. adjust application.yaml to use kafka and service registry endpoints and the create trust store
    ```
    bootstrap-servers: amqstreams-cluster-1-kafka-bootstrap-streams-playground-1.lab.redhat.com:443
    ...
    properties:
        apicurio:
            registry:
                url: http://apicurioregistry1.streams-playground-1.lab.redhat.com/api
    ```
5. generate event class
    ```
    mvn clean install -DskipTests -Pavro
    ```
6. build and package:
    ```
    mvn clean package
    ```
7. send a rest request to produce and consume events 
    ```
    curl --location --request POST 'http://localhost:8080/kafka/publish' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "some name",
        "description": "some description",
        "createdOn": 1604216455
    }'
    ```

## Maven Profiles for apicurio

### build Event Class from event.avsc
```mvn clean install -DskipTests -Pavro```
### upload schema to registry
```mvn clean install -DskipTests -Pupload```

### download schema to src/main/
```mvn clean install -DskipTests -Pdownload```

## Extract Kafka secret and import it into a java keystore
```
oc extract secret/amqstreams-cluster-1-cluster-ca-cert  -n streams-playground-1 --keys=ca.crt --to=- > ca-crt-1.pem

keytool -import -file ca-crt-1.pem -keystore ./truststore-cluster-1.jks -storepass p@ssw0rd
```

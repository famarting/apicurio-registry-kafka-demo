# spring-boot-kafka-apicurio

This projects show example of how to integrate apicurio with kafka 


1. setup kafka and a topic "events"
2. extract server certificate and import it into a java key store as trust store
3. install apicurio and import the event-example avro schema
4. adjust application.yml to use kafka and apicurio endpoints and the create trust store
5. build with maven: mvn clean package and launch the app
6. send a rest request to produce and consume events 
```
curl --location --request POST 'http://localhost:8080/kafka/publish' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "some name",
    "description": "some description 22",
    "createdOn": 1603214435
}'
```
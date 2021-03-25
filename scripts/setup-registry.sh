
http :8181/apis/registry/v2/groups/io.apicurio.example/artifacts X-Registry-ArtifactId:Event X-Registry-Version:v1 < ./schemas/event-v1.avsc
http PUT :8181/apis/registry/v2/groups/io.apicurio.example/artifacts/Event X-Registry-Version:v2 < ./schemas/event-v2.avsc
http :8181/apis/registry/v2/groups/io.apicurio.example/artifacts X-Registry-ArtifactId:Order < ./schemas/order.avsc
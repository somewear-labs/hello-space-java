# hello-space-java
Somewear can be configured to forward events to your application using webhooks. We will send http `POST` requests to your application with a JSON-formatted request body. An example of a webhook event payload can be viewed [here](sampleEvents.json).

This project is a sample Spring Boot application demonstrating a Somewear webhook implementation.

## Demo 
1. `./gradlew bootRun` - start application
2. `curl -d @sampleEvents.json -H "Content-Type: application/json" -X POST http://localhost:8080/api/somewear` - Send sample webhook payload

# hello-space-java
Sample Spring Boot application demonstrating a webhook &amp; Somewear client implementation.

## Demo 
1. `./gradlew bootRun` - start application
2. `curl -d @sampleEvents.json -H "Content-Type: application/json" -X POST http://localhost:8080/api/somewear` - Send sample webhook payload

package com.somewearlabs.hellospace.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.somewearlabs.gen.LocationProto;
import com.somewearlabs.gen.MessageProto;
import com.somewearlabs.gen.PackageProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;

@RestController("/api/somewear")
public class SomewearController {

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    void receiveWebhookEvent(@RequestBody SomewearWebhookRequest request) {
        for (SomewearWebhookRequest.WebhookData data : request.getData()) {
            handleWebhookData(data);
        }
    }

    private void handleWebhookData(SomewearWebhookRequest.WebhookData webhookData) {
        System.out.println("handleWebhookData: data=" + webhookData);

        List<SomewearWebhookRequest.WebhookEvent> events = webhookData.deserializeEvents(objectMapper);
        for (SomewearWebhookRequest.WebhookEvent event : events) {
            if (event instanceof SomewearWebhookRequest.DataEvent) {
                handleDataEvent((SomewearWebhookRequest.DataEvent)event);
            }
        }
    }

    private void handleDataEvent(SomewearWebhookRequest.DataEvent event) {
        byte[] data = Base64.getUrlDecoder().decode(event.getPayload());

        try {
            PackageProto.Package payload = PackageProto.Package.parseFrom(data);
            switch (payload.getType()) {
                case Message:
                    MessageProto.Message message = MessageProto.Message.parseFrom(payload.getData());
                    handleMessagePackage(message);
                    break;

                case Location:
                    LocationProto.Location location = LocationProto.Location.parseFrom(payload.getData());
                    handleLocationPackage(location);
                    break;

                case Unknown:
                case UNRECOGNIZED:
                    System.out.println("handleDataEvent: unknown package type; type=" + payload.getType());
            }
        }
        catch (InvalidProtocolBufferException e) {
            System.out.println("handleDataEvent: failed to parse data; error=" + e);
        }
    }

    private void handleMessagePackage(MessageProto.Message message) {
        System.out.println("handleMessagePackage: received message; message=" + message);
    }

    private void handleLocationPackage(LocationProto.Location location) {
        System.out.println("handleLocationPackage: received location; location=" + location);
    }
}

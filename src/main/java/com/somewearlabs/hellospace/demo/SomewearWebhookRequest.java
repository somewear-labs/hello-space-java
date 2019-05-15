package com.somewearlabs.hellospace.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SomewearWebhookRequest {
    private List<WebhookData> data;

    public List<WebhookData> getData() {
        return data;
    }

    public void setData(List<WebhookData> data) {
        this.data = data;
    }

    public static class WebhookData {
        private String userId;
        private String deviceId;
        private ArrayNode events;

        public List<WebhookEvent> deserializeEvents(ObjectMapper objectMapper) {
            List<WebhookEvent> webhookEvents = new ArrayList<>();

            events.elements().forEachRemaining(jsonNode -> {
                String jsonType = jsonNode.get("type").asText();

                WebhookEvent event = null;
                switch (jsonType) {
                    case "data":
                        event = objectMapper.convertValue(jsonNode, DataEvent.class);
                        break;
                    case "message":
                        event = objectMapper.convertValue(jsonNode, MessageEvent.class);
                        break;
                    case "location":
                        event = objectMapper.convertValue(jsonNode, LocationEvent.class);
                        break;
                }

                if (event != null) {
                    webhookEvents.add(event);
                }
            });
            return webhookEvents;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public ArrayNode getEvents() {
            return events;
        }

        public void setEvents(ArrayNode jsonEvents) {
            this.events = jsonEvents;
        }

        @Override
        public String toString() {
            return "WebhookData{" +
                    "userId='" + userId + '\'' +
                    ", deviceId='" + deviceId + '\'' +
                    ", events=" + events +
                    '}';
        }
    }

    public static class WebhookEvent {
        private String eventId;

        public String getEventId() {
            return eventId;
        }

        public void setEventId(String eventId) {
            this.eventId = eventId;
        }
    }

    public static class DataEvent extends WebhookEvent {
        private String payload;
        private Instant timestamp;

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "DataEvent{" +
                    "payload='" + payload + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    public static class MessageEvent extends WebhookEvent {
        private String content;
        private Instant timestamp;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "MessageEvent{" +
                    "content='" + content + '\'' +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }

    public static class LocationEvent extends WebhookEvent {
        private double latitude;
        private double longitude;
        private Instant timestamp;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "LocationEvent{" +
                    "latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", timestamp=" + timestamp +
                    '}';
        }
    }
}

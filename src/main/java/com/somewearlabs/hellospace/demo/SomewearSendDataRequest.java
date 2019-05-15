package com.somewearlabs.hellospace.demo;

public class SomewearSendDataRequest {
    private String deviceId;
    private String userId;
    private String payload;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "SomewearSendDataRequest{" +
                "deviceId='" + deviceId + '\'' +
                ", userId='" + userId + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}

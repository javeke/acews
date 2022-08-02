package com.javeke.acews.models.dao;

import com.javeke.acews.models.common.DeviceData;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;

@Document("device")
public class Device {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String deviceId;
    private String name;
    private String description;
    private String organizationId;
    private boolean enabled;
    private String status;
    private String type;

    private ArrayList<DeviceData> dataPoints;

    public Device(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<DeviceData> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(ArrayList<DeviceData> dataPoints) {
        this.dataPoints = dataPoints;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", enabled=" + enabled +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

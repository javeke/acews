package com.javeke.acews.models.dto;

import com.javeke.acews.models.dao.Device;
import org.springframework.beans.BeanUtils;

public class DeviceDto {

    private String deviceId;
    private String name;
    private String description;
    private String organizationId;
    private boolean enabled;
    private String status;
    private String type;

    public DeviceDto(){
    }

    public DeviceDto(Device device){
        BeanUtils.copyProperties(device, this);
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

    @Override
    public String toString() {
        return "DeviceDto{" +
                "deviceId='" + deviceId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", enabled=" + enabled +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

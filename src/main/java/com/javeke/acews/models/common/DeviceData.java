package com.javeke.acews.models.common;

import java.time.ZonedDateTime;

public class DeviceData {
    private String paramValue;
    private String paramName;
    private ZonedDateTime createdAt;

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "DeviceData{" +
                "paramValue='" + paramValue + '\'' +
                ", paramName='" + paramName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

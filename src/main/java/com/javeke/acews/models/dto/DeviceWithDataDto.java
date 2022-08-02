package com.javeke.acews.models.dto;

import com.javeke.acews.models.common.DeviceData;

import java.util.ArrayList;

public class DeviceWithDataDto extends DeviceDto {

    private ArrayList<DeviceData> dataPoints;

    public ArrayList<DeviceData> getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(ArrayList<DeviceData> dataPoints) {
        this.dataPoints = dataPoints;
    }

    @Override
    public String toString() {
        return "DeviceWithDataDto{" +
                "deviceId=" + getDeviceId() +
                "dataPoints=" + dataPoints +
                '}';
    }
}

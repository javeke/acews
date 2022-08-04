package com.javeke.acews.repositories;

import com.javeke.acews.models.dao.Device;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class DeviceRepositoryTest {

    @Autowired
    private IDeviceRepository deviceRepository;

    @AfterEach
    void clearDatabase() { this.deviceRepository.deleteAll(); }

    @Test
    void shouldAddDevice() {
        Device device0 = new Device();
        device0.setDeviceId("testDeviceId");
        device0.setDescription("testDescription");

        Device added = deviceRepository.save(device0);

        assertEquals(device0.getDeviceId(), added.getDeviceId());
        assertEquals(device0.getDescription(), added.getDescription());
        assertEquals(1, deviceRepository.findAll().size());
    }

    @Test
    void shouldGetAllDevices(){
        Device device0 = new Device();
        device0.setDeviceId("testDeviceId0");
        device0.setDescription("testDescription0");

        Device device1 = new Device();
        device1.setDeviceId("testDeviceId1");
        device1.setDescription("testDescription1");

        deviceRepository.saveAll(Arrays.asList(device0, device1));

        assertEquals(2, deviceRepository.findAll().size());
    }

    @Test
    void shouldReturnNullWhenNoDeviceFoundWithDeviceId(){
        String deviceId = "testId";
        Device device = deviceRepository.findByDeviceId(deviceId);
        assertNull(device);
    }

    @Test
    void shouldReturnDeviceWhenDeviceFoundWithDeviceId(){
        String deviceId = "testId";

        Device device0 = new Device();
        device0.setDeviceId(deviceId);
        device0.setDescription("testDescription0");

        deviceRepository.save(device0);

        Device found = deviceRepository.findByDeviceId(deviceId);
        assertNotNull(found);
        assertEquals(deviceId, found.getDeviceId());
        assertEquals(device0.getDescription(), found.getDescription());
    }
}

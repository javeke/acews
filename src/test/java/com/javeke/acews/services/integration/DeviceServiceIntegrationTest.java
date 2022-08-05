package com.javeke.acews.services.integration;

import com.javeke.acews.models.dto.DeviceDto;
import com.javeke.acews.repositories.IDeviceRepository;
import com.javeke.acews.services.DeviceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class DeviceServiceIntegrationTest {

    @Autowired
    private IDeviceRepository deviceRepository;

    private DeviceService deviceService;

    @BeforeEach
    void setUp() { this.deviceService = new DeviceService(deviceRepository); }

    @AfterEach
    void clearDatabase() { this.deviceRepository.deleteAll(); }

    @Test
    void shouldReturnEmptyListWhenNoDevicesExists(){
        List<DeviceDto> deviceDtos = deviceService.getDevices();
        assertEquals(0, deviceDtos.size());
    }

    @Test
    void shouldReturnEmptyListWhenNoDevicesExistsWithOrganizationId(){
        String organizationId = "testOrgId";
        List<DeviceDto> deviceDtos = deviceService.getDevicesByOrganizationId(organizationId);
        assertEquals(0, deviceDtos.size());
    }

    @Test
    void shouldReturnNullWhenNoDeviceExists(){
        String deviceId = "testDeviceId";
        DeviceDto device = deviceService.getDeviceByDeviceId(deviceId);
        assertNull(device);
    }
}

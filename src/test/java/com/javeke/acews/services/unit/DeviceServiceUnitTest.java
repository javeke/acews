package com.javeke.acews.services.unit;

import com.javeke.acews.models.dao.Device;
import com.javeke.acews.models.dto.DeviceDto;
import com.javeke.acews.repositories.IDeviceRepository;
import com.javeke.acews.services.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
class DeviceServiceUnitTest {

    @MockBean
    private IDeviceRepository deviceRepository;

    private DeviceService deviceService;

    @BeforeEach
    void setUp(){ this.deviceService = new DeviceService(this.deviceRepository); }


    @Test
    void shouldReturnEmptyListWhenNoDevicesExist(){
        when(deviceRepository.findAll()).thenReturn(new ArrayList<>());
        List<DeviceDto> devices = deviceService.getDevices();
        assertEquals(0, devices.size());
    }

    @Test
    void shouldReturnEmptyListWhenNoDevicesExistWithOrganizationId(){
        String organizationId = "testId0";
        when(deviceRepository.findAllByOrganizationId(organizationId)).thenReturn(new ArrayList<>());
        List<DeviceDto> devices = deviceService.getDevicesByOrganizationId(organizationId);
        assertEquals(0, devices.size());
    }

    @Test
    void shouldReturnNullWhenNoDevicesExistWithId(){
        String deviceId = "testId0";
        when(deviceRepository.findByDeviceId(deviceId)).thenReturn(null);
        DeviceDto device = deviceService.getDeviceByDeviceId(deviceId);
        assertNull(device);
    }

    @Test
    void shouldGetAllDevice(){
        Device device0 = new Device();
        device0.setDeviceId("testId0");
        device0.setName("testName0");
        device0.setOrganizationId("testOrgId0");
        device0.setEnabled(true);

        Device device1 = new Device();
        device1.setDeviceId("testId1");
        device1.setName("testName1");
        device1.setOrganizationId("testOrgId1");
        device1.setEnabled(true);

        Device device2 = new Device();
        device2.setDeviceId("testId2");
        device2.setName("testName2");
        device2.setOrganizationId("testOrgId2");
        device2.setEnabled(true);

        when(deviceRepository.findAll()).thenReturn(Arrays.asList(device0, device1, device2));

        List<DeviceDto> deviceDtos = deviceService.getDevices();

        assertEquals(device0.getDeviceId(), deviceDtos.get(0).getDeviceId());
        assertEquals(device0.getDescription(), deviceDtos.get(0).getDescription());
        assertEquals(device0.getName(), deviceDtos.get(0).getName());
        assertEquals(device0.isEnabled(), deviceDtos.get(0).isEnabled());

        assertEquals(device1.getDeviceId(), deviceDtos.get(1).getDeviceId());
        assertEquals(device1.getDescription(), deviceDtos.get(1).getDescription());
        assertEquals(device1.getName(), deviceDtos.get(1).getName());
        assertEquals(device1.isEnabled(), deviceDtos.get(1).isEnabled());

        assertEquals(device2.getDeviceId(), deviceDtos.get(2).getDeviceId());
        assertEquals(device2.getDescription(), deviceDtos.get(2).getDescription());
        assertEquals(device2.getName(), deviceDtos.get(2).getName());
        assertEquals(device2.isEnabled(), deviceDtos.get(2).isEnabled());
    }
}

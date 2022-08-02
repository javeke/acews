package com.javeke.acews.services;

import com.javeke.acews.models.dao.Device;
import com.javeke.acews.models.dto.DeviceDto;
import com.javeke.acews.repositories.IDeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    private IDeviceRepository deviceRepository;
    private static final Logger logger = LoggerFactory.getLogger(DeviceService.class);

    @Autowired
    public DeviceService(IDeviceRepository deviceRepository){
        this.deviceRepository = deviceRepository;
    }

    public List<DeviceDto> getDevices(){
        return deviceRepository.findAll().stream().map(DeviceDto::new).collect(Collectors.toList());
    }
    public List<DeviceDto> getDevicesByOrganizationId(String organizationId){
        return deviceRepository.findAllByOrganizationId(organizationId).stream().map(DeviceDto::new).collect(Collectors.toList());
    }

    public DeviceDto getDeviceByDeviceId(String deviceId){
        Device found = deviceRepository.findByDeviceId(deviceId);

        if(found == null){
            return null;
        }
        DeviceDto response = new DeviceDto();
        BeanUtils.copyProperties(found, response);
        return response;
    }
}

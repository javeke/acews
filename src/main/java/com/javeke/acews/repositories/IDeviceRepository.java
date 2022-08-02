package com.javeke.acews.repositories;

import com.javeke.acews.models.dao.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IDeviceRepository  extends MongoRepository<Device, String> {
    Device findByDeviceId(String deviceId);
    List<Device> findAllByOrganizationId(String organizationId);
    int deleteByDeviceId(String deviceId);
}

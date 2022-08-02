package com.javeke.acews.repositories;

import com.javeke.acews.models.dao.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IOrganizationRepository extends MongoRepository<Organization, String> {
    Organization findByOrganizationId(String organizationId);
    int deleteByOrganizationId(String organizationId);
}

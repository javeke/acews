package com.javeke.acews.repositories;

import com.javeke.acews.models.dao.Organization;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class OrganizationRepositoryTest {
    @Autowired
    private IOrganizationRepository organizationRepository;

    @AfterEach
    void clearDatabase() { organizationRepository.deleteAll(); }

    @Test
    void shouldAddOrganization(){
        Organization testOrganization =  new Organization();
        testOrganization.setOrganizationId("testid");
        testOrganization.setOrganizationId("testname");
        testOrganization.setOrganizationId("testdescription");

        organizationRepository.save(testOrganization);

        assertEquals(1, organizationRepository.findAll().size());
    }

    @Test
    void shouldGetAllOrganizations(){
        Organization testOrganization0 =  new Organization();
        testOrganization0.setOrganizationId("testid0");
        testOrganization0.setOrganizationId("testname0");
        testOrganization0.setOrganizationId("testdescription0");

        Organization testOrganization1 =  new Organization();
        testOrganization1.setOrganizationId("testid1");
        testOrganization1.setOrganizationId("testname2");
        testOrganization1.setOrganizationId("testdescription1");

        Organization testOrganization2 =  new Organization();
        testOrganization2.setOrganizationId("testid2");
        testOrganization2.setOrganizationId("testname2");
        testOrganization2.setOrganizationId("testdescription2");

        Organization testOrganization3 =  new Organization();
        testOrganization3.setOrganizationId("testid3");
        testOrganization3.setOrganizationId("testname3");
        testOrganization3.setOrganizationId("testdescription3");

        organizationRepository.saveAll(Arrays.asList(testOrganization0, testOrganization1, testOrganization2, testOrganization3));

        assertEquals(4, organizationRepository.findAll().size());
    }

    @Test
    void shouldGetOrganizationByOrganizationId(){
        Organization testOrganization0 =  new Organization();
        testOrganization0.setOrganizationId("testid0");
        testOrganization0.setOrganizationId("testname0");
        testOrganization0.setOrganizationId("testdescription0");

        organizationRepository.save(testOrganization0);

        Organization found = organizationRepository.findByOrganizationId(testOrganization0.getOrganizationId());

        assertNotNull(found);
        assertEquals(testOrganization0.getName(), found.getName());
        assertEquals(testOrganization0.getOrganizationId(), found.getOrganizationId());
        assertEquals(testOrganization0.getDescription(), found.getDescription());
    }

    @Test
    void shouldReturnNullWhenOrganizationNotFound(){
        Organization found = organizationRepository.findByOrganizationId("testid0");
        assertNull(found);
    }
}

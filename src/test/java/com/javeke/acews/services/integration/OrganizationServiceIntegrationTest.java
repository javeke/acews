package com.javeke.acews.services.integration;

import com.javeke.acews.models.dao.Organization;
import com.javeke.acews.models.dto.OrganizationDto;
import com.javeke.acews.models.dto.UpdateOrganizationDto;
import com.javeke.acews.repositories.IOrganizationRepository;
import com.javeke.acews.services.OrganizationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class OrganizationServiceIntegrationTest {

    @Autowired
    private IOrganizationRepository organizationRepository;

    private OrganizationService organizationService;

    @BeforeEach
    void setUp() { this.organizationService = new OrganizationService(organizationRepository); }

    @AfterEach
    void clearDatabase() { this.organizationRepository.deleteAll(); }

    @Test
    void shouldReturnEmptyListWhenNoOrganizationsExists(){
        List<OrganizationDto> organizationDtos = organizationService.getOrganizations();
        assertEquals(0, organizationDtos.size());
    }

    @Test
    void shouldReturnNullWhenNoOrganizationsExistsWithOrganizationId(){
        String organizationId = "testOrgId";
        OrganizationDto organization = organizationService.getOrganizationByOrganizationId(organizationId);
        assertNull(organization);
    }

    @Test
    void shouldAddAndRetrieveOrganizationFromDB(){
        OrganizationDto organization = new OrganizationDto();
        organization.setName("testName");
        organization.setDescription("testDescription");

        OrganizationDto addedOrg = organizationService.addOrganization(organization);

        assertNotNull(addedOrg.getOrganizationId());
        assertEquals(6, addedOrg.getOrganizationId().length());
        assertEquals(organization.getName(), addedOrg.getName());
        assertEquals(organization.getDescription(), addedOrg.getDescription());

        OrganizationDto found = organizationService.getOrganizationByOrganizationId(addedOrg.getOrganizationId());

        assertEquals(addedOrg.getOrganizationId(), found.getOrganizationId());
        assertEquals(addedOrg.getName(), found.getName());
        assertEquals(addedOrg.getDescription(), found.getDescription());
    }

    @Test
    void shouldUpdateOrganizationWithUpdateData(){
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setName("testName");
        organizationDto.setDescription("testDescription");

        OrganizationDto addedOrg = organizationService.addOrganization(organizationDto);

        UpdateOrganizationDto updateOrganizationDto = new UpdateOrganizationDto();
        updateOrganizationDto.setDescription("updateDescription");
        updateOrganizationDto.setName("updateName");

        OrganizationDto updatedOrganizationDto = organizationService.updateOrganization(addedOrg.getOrganizationId(), updateOrganizationDto);

        assertEquals(updateOrganizationDto.getName(), updatedOrganizationDto.getName());
        assertEquals(updateOrganizationDto.getDescription(), updatedOrganizationDto.getDescription());
    }

    @Test
    void shouldRemoveOrganizationWithOrganizationId(){
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setName("testName");
        organizationDto.setDescription("testDescription");

        OrganizationDto addedOrg = organizationService.addOrganization(organizationDto);

        OrganizationDto found = organizationService.getOrganizationByOrganizationId(addedOrg.getOrganizationId());

        assertEquals(found.getOrganizationId(), addedOrg.getOrganizationId());
        assertEquals(found.getName(), addedOrg.getName());
        assertEquals(found.getDescription(), addedOrg.getDescription());

        boolean isRemoved = organizationService.removeOrganization(addedOrg.getOrganizationId());

        assertTrue(isRemoved);

        found = organizationService.getOrganizationByOrganizationId(addedOrg.getOrganizationId());
        assertNull(found);

        assertEquals(0, organizationService.getOrganizations().size());
    }
}

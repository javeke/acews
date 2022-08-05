package com.javeke.acews.services.unit;

import com.javeke.acews.models.dao.Organization;
import com.javeke.acews.models.dto.OrganizationDto;
import com.javeke.acews.models.dto.UpdateOrganizationDto;
import com.javeke.acews.repositories.IOrganizationRepository;
import com.javeke.acews.services.OrganizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
class OrganizationServiceUnitTest {

    @MockBean
    private IOrganizationRepository organizationRepository;

    private OrganizationService organizationService;

    @BeforeEach
    void setUp(){ this.organizationService = new OrganizationService(this.organizationRepository); }

    @Test
    void shouldReturnEmptyListWhenNoOrganizationsExist(){
        List<OrganizationDto> organizations = organizationService.getOrganizations();
        assertEquals(0, organizations.size());
    }

    @Test
    void shouldReturnNullWhenNoOrganizationsExistWithOrganizationId() {
        String organizationId = "testid";
        OrganizationDto found = organizationService.getOrganizationByOrganizationId(organizationId);
        assertNull(found);
    }

    @Test
    void shouldAddOrganizationToDB() {
        OrganizationDto organizationDto0 = new OrganizationDto("testName0", "testDescription0");
        OrganizationDto organizationDto1 = new OrganizationDto("testName1", "testDescription1");

        OrganizationDto added0 = organizationService.addOrganization(organizationDto0);
        OrganizationDto added1 = organizationService.addOrganization(organizationDto1);

        Organization org0 = new Organization();
        Organization org1 = new Organization();

        BeanUtils.copyProperties(added0, org0);
        BeanUtils.copyProperties(added1, org1);

        when(organizationRepository.findAll()).thenReturn(Arrays.asList(org0, org1));

        assertEquals(organizationDto0.getOrganizationId(), added0.getOrganizationId());
        assertEquals(organizationDto0.getName(), added0.getName());
        assertEquals(organizationDto0.getDescription(), added0.getDescription());
        assertEquals(organizationDto1.getOrganizationId(), added1.getOrganizationId());
        assertEquals(organizationDto1.getName(), added1.getName());
        assertEquals(organizationDto1.getDescription(), added1.getDescription());

        assertEquals(2, organizationService.getOrganizations().size());
    }

    @Test
    void shouldUpdateOrganizationWithParamData(){
        UpdateOrganizationDto updateOrganizationDto = new UpdateOrganizationDto();
        updateOrganizationDto.setName("updateTestName");
        updateOrganizationDto.setDescription("updateDescription");

        Organization  organization = new Organization();
        BeanUtils.copyProperties(updateOrganizationDto, organization);
        organization.setOrganizationId("testId");

        when(organizationRepository.findByOrganizationId("testId")).thenReturn(organization);
        when(organizationRepository.save(organization)).thenReturn(organization);

        OrganizationDto updatedResponse = organizationService.updateOrganization(organization.getOrganizationId(), updateOrganizationDto);

        assertEquals(organization.getOrganizationId(), updatedResponse.getOrganizationId());
        assertEquals(updateOrganizationDto.getName(), updatedResponse.getName());
        assertEquals(updateOrganizationDto.getDescription(), updatedResponse.getDescription());
    }

    @Test
    void shouldUpdateOrganizationWithNonNullParamData(){
        UpdateOrganizationDto updateOrganizationDto = new UpdateOrganizationDto();
        updateOrganizationDto.setName("updateTestName");

        Organization  organization = new Organization();
        organization.setOrganizationId("testId");
        organization.setName("testName");

        when(organizationRepository.findByOrganizationId("testId")).thenReturn(organization);
        BeanUtils.copyProperties(updateOrganizationDto, organization);
        organization.setDescription("testDescription");
        when(organizationRepository.save(organization)).thenReturn(organization);

        OrganizationDto updatedResponse = organizationService.updateOrganization(organization.getOrganizationId(), updateOrganizationDto);

        assertEquals(organization.getOrganizationId(), updatedResponse.getOrganizationId());
        assertEquals(updateOrganizationDto.getName(), updatedResponse.getName());
        assertNotEquals(updateOrganizationDto.getDescription(), updatedResponse.getDescription());
    }

    @Test
    void shouldRemoveOrganizationWithOrganizationId() {
        String organizationId = "testId";
        when(organizationRepository.deleteByOrganizationId(organizationId)).thenReturn(1);

        boolean isDeleted = organizationService.removeOrganization(organizationId);
        assertEquals(true, isDeleted);
    }
}

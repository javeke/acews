package com.javeke.acews.services;

import com.javeke.acews.models.dao.Organization;
import com.javeke.acews.repositories.IOrganizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class OrganizationServiceTest {

    @MockBean
    private IOrganizationRepository organizationRepository;

    private OrganizationService organizationService;

    @BeforeEach
    void setUp(){ this.organizationService = new OrganizationService(this.organizationRepository); }

    @Test
    void shouldReturnEmptyListWhenNoOrganizationsExist(){
        List<Organization> organizations = organizationService.getOrganizations();

        assertEquals(organizations.size(), 0);
    }
}

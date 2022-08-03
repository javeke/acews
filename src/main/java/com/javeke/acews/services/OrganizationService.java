package com.javeke.acews.services;

import com.javeke.acews.models.dao.Organization;
import com.javeke.acews.models.dto.OrganizationDto;
import com.javeke.acews.models.dto.UpdateOrganizationDto;
import com.javeke.acews.repositories.IOrganizationRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationService {

    private final IOrganizationRepository organizationRepository;
    private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);

    @Autowired
    public OrganizationService(IOrganizationRepository organizationRepository){
        this.organizationRepository = organizationRepository;
    }

    public List<OrganizationDto> getOrganizations(){

        return organizationRepository.findAll().stream().map(OrganizationDto::new).collect(Collectors.toList());
    }

    public OrganizationDto getOrganizationByOrganizationId(String organizationId){
        Organization found =  organizationRepository.findByOrganizationId(organizationId);

        return found == null ? null : new OrganizationDto(found);
    }

    public OrganizationDto addOrganization(OrganizationDto organizationDto){
        String oId = RandomStringUtils.randomAlphabetic(6);
        while (organizationRepository.findByOrganizationId(oId) != null){
            oId =  RandomStringUtils.randomAlphabetic(6);
        }
        logger.info("Trying to add new organization with id: {}", oId);
        organizationDto.setOrganizationId(oId);
        Organization newOrganization = new Organization();
        BeanUtils.copyProperties(organizationDto, newOrganization);
        organizationRepository.save(newOrganization);
        logger.info("Added new organization with id: {}", oId);
        return organizationDto;
    }

    public OrganizationDto updateOrganization(String organizationId, UpdateOrganizationDto updateOrganizationDto){
        Organization organization = organizationRepository.findByOrganizationId(organizationId);
        logger.info("Found organization with id: {}", organizationId);
        if(organization == null) {
            return null;
        }

        if (updateOrganizationDto.getName() != null){
            organization.setName(updateOrganizationDto.getName());
        }

        if (updateOrganizationDto.getDescription() != null){
            organization.setDescription(updateOrganizationDto.getDescription());
        }

        OrganizationDto response = new OrganizationDto();
        organization = organizationRepository.save(organization);
        logger.info("Saved changes to organization with id: {}", organizationId);
        BeanUtils.copyProperties(organization, response);
        return  response;
    }

    public boolean removeOrganization(String organizationId){
        logger.info("Trying to delete organization with id: {}", organizationId);
        return organizationRepository.deleteByOrganizationId(organizationId) > 0;
    }
}

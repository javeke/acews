package com.javeke.acews.models.dto;

import com.javeke.acews.models.dao.Organization;
import org.springframework.beans.BeanUtils;

public class OrganizationDto {

    private String organizationId;
    private String name;
    private String description;

    public OrganizationDto(){
        this.organizationId = "";
        this.name = "";
        this.description = "";
    }

    public OrganizationDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public OrganizationDto(Organization organization){
        BeanUtils.copyProperties(organization, this);
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OrganizationDto{" +
                "organizationId='" + organizationId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

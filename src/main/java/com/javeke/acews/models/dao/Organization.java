package com.javeke.acews.models.dao;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("organization")
public class Organization {

    @MongoId(FieldType.OBJECT_ID)
    private String id;

    private String organizationId;
    private String name;
    private String description;


    public Organization(){
        this.organizationId = "";
        this.name = "";
        this.description = "";
    }

    public Organization(String organizationId, String name, String description) {
        this.organizationId = organizationId;
        this.name = name;
        this.description = description;
    }

    public Organization(String id, String organizationId, String name, String description) {
        this.id = id;
        this.organizationId = organizationId;
        this.name = name;
        this.description = description;
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
        return "Organization{" +
                "id='" + id + '\'' +
                ", organizationId='" + organizationId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

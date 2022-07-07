package com.engagementMusic.engagementMusic.DTO;

import com.engagementMusic.engagementMusic.Embeddables.Address;
import com.engagementMusic.engagementMusic.Models.Agent;

import javax.persistence.Embedded;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DancingBarDTO {

    private Agent agentDancing;
    @NotEmpty
    @NotNull
    private String name;

    private String picture;
    @Embedded
    @NotNull
    private Address address;

    public DancingBarDTO(Agent agentDancing, String name, String picture, Address address) {
        this.agentDancing = agentDancing;
        setName(name);
        this.picture = picture;
        this.address = address;
    }

    public DancingBarDTO() {}

    public Agent getAgentDancing() {
        return agentDancing;
    }

    public void setAgentDancing(Agent agentDancing) {
        this.agentDancing = agentDancing;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

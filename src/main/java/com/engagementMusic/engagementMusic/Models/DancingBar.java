package com.engagementMusic.engagementMusic.Models;

import com.engagementMusic.engagementMusic.Embeddables.Address;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Entity
public class DancingBar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "agentId")
    private Agent agentDancing;
    private String name;
    private String picture;
    @Embedded
    private Address address;

    public DancingBar(Agent agentDancing, String name, Address address) {
        this.agentDancing = agentDancing;
        setName(name);
        this.picture = "https://cdn-icons-png.flaticon.com/512/335/335954.png";
        this.address = address;
    }

    public DancingBar(Agent agentDancing, String name, String picture, Address address) {
        this.agentDancing = agentDancing;
        setName(name);
        this.picture = picture;
        this.address = address;
    }

    public DancingBar() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

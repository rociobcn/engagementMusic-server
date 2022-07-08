package com.engagementMusic.engagementMusic.Models;

import com.engagementMusic.engagementMusic.Embeddables.FullName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Agent extends User{

    private String position;
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)

    private List<Booking> bookingListAgent;
    @OneToMany(mappedBy = "agentDancing", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DancingBar> dancingBarListAgent;

    public Agent(FullName fullName, String email, String username, String password, String dni, long phone, String position) {
        super(fullName, email, username, password, dni, phone);
        setPosition(position);
    }

    public Agent(FullName fullName, String email, String username, String password, String picture, String dni, long phone, String position) {
        super(fullName, email, username, password, picture, dni, phone);
        setPosition(position);
    }

    public Agent() {}

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position.toUpperCase();
    }

    public List<Booking> getBookingListAgent() {
        return bookingListAgent;
    }

    public void setBookingListAgent(List<Booking> bookingListAgent) {
        this.bookingListAgent = bookingListAgent;
    }

    public List<DancingBar> getDancingBarListAgent() {
        return dancingBarListAgent;
    }

    public void setDancingBarListAgent(List<DancingBar> dancingBarListAgent) {
        this.dancingBarListAgent = dancingBarListAgent;
    }
}

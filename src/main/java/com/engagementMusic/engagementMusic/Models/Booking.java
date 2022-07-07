package com.engagementMusic.engagementMusic.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "bandId")

    private Band band;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "agentId")
    private Agent agent;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private BigDecimal priceTotal;

    public Booking(){}

    public Booking(Band band, Agent agent, LocalDate date, BigDecimal priceTotal) {
        this.band = band;
        this.agent = agent;
        this.date = date;
        this.priceTotal = priceTotal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(BigDecimal priceTotal) {
        this.priceTotal = priceTotal;
    }
}

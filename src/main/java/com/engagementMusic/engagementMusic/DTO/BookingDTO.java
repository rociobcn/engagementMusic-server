package com.engagementMusic.engagementMusic.DTO;

import com.engagementMusic.engagementMusic.Models.Agent;
import com.engagementMusic.engagementMusic.Models.Band;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


public class BookingDTO {

    private Band band;

    private Agent agent;

    @NotNull
    private LocalDate date;

    @NotNull
    private BigDecimal priceTotal;

    public BookingDTO() {}

    public BookingDTO(Band band, Agent agent, LocalDate date, BigDecimal priceTotal) {
        this.band = band;
        this.agent = agent;
        this.date = date;
        this.priceTotal = priceTotal;
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

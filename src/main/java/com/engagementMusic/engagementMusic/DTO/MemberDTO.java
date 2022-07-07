package com.engagementMusic.engagementMusic.DTO;

import com.engagementMusic.engagementMusic.Embeddables.FullName;
import com.engagementMusic.engagementMusic.Enums.Instrument;
import com.engagementMusic.engagementMusic.Models.Band;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class MemberDTO {
    @Embedded
    @NotNull
    private FullName fullName;

    @NotNull
    private boolean single;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Instrument instrument;


    private Band band;

    public MemberDTO(FullName fullName, boolean single, Instrument instrument, Band band) {
        this.fullName = fullName;
        this.single = single;
        this.instrument = instrument;
        this.band = band;
    }

    public MemberDTO() {}

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public boolean isSingle() {
        return single;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }
}

package com.engagementMusic.engagementMusic.Models;

import com.engagementMusic.engagementMusic.Embeddables.FullName;
import com.engagementMusic.engagementMusic.Enums.Instrument;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "memberFullName")),
            @AttributeOverride(name = "lastName", column = @Column(name = "memberLastName"))
    })
    private FullName fullName;
    private boolean single;
    @Enumerated(EnumType.STRING)
    private Instrument instrument;
    @ManyToOne
    @JoinColumn(name = "bandId")
    private Band band;

    public Member(FullName fullName, boolean single, Instrument instrument, Band band) {
        this.fullName = fullName;
        this.single = single;
        this.instrument = instrument;
        this.band = band;
    }

    public Member() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
}

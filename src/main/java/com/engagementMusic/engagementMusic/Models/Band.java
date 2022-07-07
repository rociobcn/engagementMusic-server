package com.engagementMusic.engagementMusic.Models;

import com.engagementMusic.engagementMusic.Embeddables.FullName;
import com.engagementMusic.engagementMusic.Enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;
@Entity
public class Band extends User{


    private String nameOfGroup;
    @OneToMany(mappedBy = "band")
    @JsonIgnore
    private List<Member> membersList;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private BigDecimal priceHour;
    @OneToMany(mappedBy = "band")
    private List <Booking> BookingList;

    public Band(FullName fullName, String email, String username, String password, String dni, long phone, String nameOfGroup, Genre genre, BigDecimal priceHour) {
        super(fullName, email, username, password, dni, phone);
        this.nameOfGroup = nameOfGroup;
        this.genre = genre;
        this.priceHour = priceHour;
    }

    public Band(FullName fullName, String email, String username, String password, String picture, String dni, long phone, String nameOfGroup, Genre genre, BigDecimal priceHour) {
        super(fullName, email, username, password, picture, dni, phone);
        this.nameOfGroup = nameOfGroup;
        this.genre = genre;
        this.priceHour = priceHour;
    }

    public Band() {}

    public String getNameOfGroup() {
        return nameOfGroup;
    }

    public void setNameOfGroup(String nameOfGroup) {
        this.nameOfGroup = nameOfGroup.toUpperCase();
    }

    public List<Member> getMembersList() {
        return membersList;
    }

    public void setMembersList(List<Member> membersList) {
        this.membersList = membersList;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public BigDecimal getPriceHour() {
        return priceHour;
    }

    public void setPriceHour(BigDecimal priceHour) {
        this.priceHour = priceHour;
    }

    public List<Booking> getBookingList() {
        return BookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        BookingList = bookingList;
    }
}

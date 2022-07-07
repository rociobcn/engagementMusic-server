package com.engagementMusic.engagementMusic.DTO;

import com.engagementMusic.engagementMusic.Embeddables.FullName;
import com.engagementMusic.engagementMusic.Enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


public class AgentDTO {

    private FullName fullName;

    private String email;

    private String username;

    private String password;

    private String picture;

    private String dni;
    private long phone;

    private String position;

    public AgentDTO() {}

    public AgentDTO(FullName fullName, String email, String username, String password, String picture, String dni, long phone, String position) {
        this.fullName = fullName;
        this.email = email;
        setUsername(username);
        this.password = password;
        this.picture = picture;
        this.dni = dni;
        this.phone = phone;
        setPosition(position);
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username.toUpperCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position.toUpperCase();
    }
}

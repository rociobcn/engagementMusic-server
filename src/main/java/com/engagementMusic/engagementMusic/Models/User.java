package com.engagementMusic.engagementMusic.Models;

import com.engagementMusic.engagementMusic.Embeddables.FullName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "userFirstName")),
            @AttributeOverride(name = "lastName", column = @Column(name = "userLastName"))
    })

    private FullName fullName;
    @Column(unique = true)
    private String email;
    @Id
    private String username;
    private String password;
    private String picture;
    private String dni;
    private long phone;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Role> roles = new HashSet<>();

    public User() {}

    public User(FullName fullName, String email, String username, String password, String dni, long phone) {
        this.fullName = fullName;
        this.email = email;
        setUsername(username);
        this.password = password;
        this.picture = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/User_font_awesome.svg/2048px-User_font_awesome.svg.png";
        this.dni = dni;
        this.phone = phone;
    }

    public User(FullName fullName, String email, String username, String password, String picture, String dni, long phone) {
        this.fullName = fullName;
        this.email = email;
        setUsername(username);
        this.password = password;
        this.picture = picture;
        setDni(dni);
        this.phone = phone;
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
        if(dniValid(dni)){
            this.dni = dni;
        } else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "DNI isn't valid");
        }

    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setRole(String role) {
        roles.add(new Role(role, this));
    }

    public boolean dniValid(String dni){
        int numDni;
        String letterDni = dni.substring(8, 9).toUpperCase();
        String [] dniArray = new String []{"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D","X", "B", "N", "J", "Z", "S","Q", "V", "H", "L", "C", "K", "E" };
        boolean result = false;
        int resto;
        String numero = dni.substring(0, 8);
        numDni = Integer.parseInt(numero) ;


        resto = numDni % 23;
        if(letterDni.equals(dniArray[resto])){
            return true;
        } else{
            return false;
        }


    }
}

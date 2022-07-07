package com.engagementMusic.engagementMusic.Embeddables;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;

@Embeddable
public class FullName {

    private String firstName;

    private String lastName;

    public FullName(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }

    public FullName() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toUpperCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase();
    }
}

package com.group10.se452_g10.model;

import java.util.UUID;

public class admin {

    private UUID adminId;
    private String firstName;
    private String lastName;

    private String email;

    public admin(String firstName, String lastName) {

        this.adminId  = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = firstName.substring(0,2) +
                lastName.substring(0,2) + "@depaul.edu";
    }
}

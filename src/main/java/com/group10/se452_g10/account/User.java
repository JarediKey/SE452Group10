package com.group10.se452_g10.account;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
//Seperating User fields
@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class User extends Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String email;
    @Column(name = "first_name")
    protected String firstName;
    @Column(name = "last_name")
    protected String lastName;
    protected long phoneNumber;
    protected String address;
    protected long ssn;
    protected Date dob;
    protected String gender;

    public User(){
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

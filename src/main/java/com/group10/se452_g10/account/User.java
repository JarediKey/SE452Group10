package com.group10.se452_g10.account;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class User extends Account {
    protected String email;
    protected String firstName;
    protected String lastName;
    protected long phoneNumber;
    protected String address;
    protected long ssn;
    protected Date dob;
    protected String gender;
    protected String guardian_name;
    protected int guardian_number;
}

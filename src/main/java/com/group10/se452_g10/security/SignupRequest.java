package com.group10.se452_g10.security;

import com.group10.se452_g10.account.UserRoleType;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String username;

    private String email;

    private UserRoleType role;

    private String password;

    private String firstName;
    private String lastName;
    private String gender;
    private Date dob;
}

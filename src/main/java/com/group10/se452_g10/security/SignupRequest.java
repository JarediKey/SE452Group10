package com.group10.se452_g10.security;

import com.group10.se452_g10.account.UserRoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    private String username;

    private String email;

    private UserRoleType role;

    private String password;
}

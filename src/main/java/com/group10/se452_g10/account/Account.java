package com.group10.se452_g10.account;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor

public abstract class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 20)
    protected String username;

    @NotBlank
    @Size(max = 120)
    protected String password;
}

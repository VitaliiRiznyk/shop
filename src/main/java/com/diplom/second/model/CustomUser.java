package com.diplom.second.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    private String password;

    private String email;

    private boolean isVerified;

    @Enumerated(EnumType.STRING)
    private CustomUserRole role;

    public CustomUser(String login, String password,  String email, CustomUserRole role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.isVerified = false;
        this.role = role;
    }

    public CustomUser() {
    }
}
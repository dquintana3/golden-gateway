package com.example.goldengateway.database;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
}

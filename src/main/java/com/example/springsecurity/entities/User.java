package com.example.springsecurity.entities;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Column(unique = true)
    private String username;
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

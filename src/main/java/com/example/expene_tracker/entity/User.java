package com.example.expene_tracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Pattern(regexp = "^[a-zA-Z\\s]*$")
    @Column(nullable = false, name = "Name" , length = 255)
    private String name;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true, name="Email" )
    private String email;

    @NotBlank
    @Size(min = 6)
    @Column(nullable = false, name="Password")
    private String password;

// getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

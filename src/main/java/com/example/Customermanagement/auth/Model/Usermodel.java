package com.example.Customermanagement.auth.Model;

import jakarta.persistence.*;

@Entity
@Table
public class Usermodel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Userroles role;


    public Usermodel() {
    }

    public Usermodel(Long id, String username, String password, Userroles role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Usermodel(String username, String password, Userroles role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Userroles getRole() {
        return role;
    }

    public void setRole(Userroles role) {
        this.role = role;
    }
}

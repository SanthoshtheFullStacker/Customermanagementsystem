package com.example.Customermanagement.DTO.Request;

import com.example.Customermanagement.auth.Model.Userroles;

public class Usermodelrequest {
    private String username;
    private String password;
    private Userroles role;


    public Usermodelrequest(String username, String password, Userroles role) {
        this.username = username;
        this.password = password;
        this.role = role;
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

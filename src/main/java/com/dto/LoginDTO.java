package com.dto;

import java.io.Serializable;

public class LoginDTO implements Serializable{
    private static final long serialVersionUID = 6861126597590411218L;
    private String username;
    private String password;

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
}
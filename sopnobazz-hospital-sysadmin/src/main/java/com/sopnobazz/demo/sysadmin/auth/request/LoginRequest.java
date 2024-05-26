package com.sopnobazz.demo.sysadmin.auth.request;

/**
 * @version 1.0.0
 * @Project hospital-management
 * @Author Md. Nayeemul Islam
 * @Since May 28, 2021
 */
public class LoginRequest {

    public String username;
    public String password;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        super();
        this.username = username;
        this.password = password;
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


}

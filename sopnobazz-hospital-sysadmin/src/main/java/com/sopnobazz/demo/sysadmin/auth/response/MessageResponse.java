package com.sopnobazz.demo.sysadmin.auth.response;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public class MessageResponse {

    public String message;


    public MessageResponse() {
    }


    public MessageResponse(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


}

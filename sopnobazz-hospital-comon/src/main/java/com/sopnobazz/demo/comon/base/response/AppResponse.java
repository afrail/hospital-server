package com.sopnobazz.demo.comon.base.response;

import java.util.Date;

import com.sopnobazz.demo.comon.base.model.MetaModel;
import org.springframework.http.HttpStatus;


/**
 * @version 1.0.0
 * @Project hospital-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 23, 2022
 */


public class AppResponse {

    private Date timestamp;
    private HttpStatus httpStatus;
    private Boolean status;
    private String message;
    private Object data;
    private MetaModel meta;
    private Object header;
    private String messageBn;


    private AppResponse(HttpStatus httpStatus, Boolean status) {
        this.httpStatus = httpStatus;
        this.status = status;
        this.timestamp = new Date();
    }

    private AppResponse() {
    }

    public static AppResponse build(HttpStatus httpStatus, Boolean status) {
        return new AppResponse(httpStatus, status);
    }

    public Integer getHttpStatus() {
        return httpStatus.value();
    }

    public Boolean getStatus() {
        return this.status;
    }


    public AppResponse messageBn(String messageBn) {
        this.messageBn = messageBn;
        return this;
    }

    public String getMessageBn() {
        return messageBn;
    }

    public AppResponse message(String message) {
        this.message = message;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }


    public Object getData() {
        return data;
    }

    public AppResponse data(Object data) {
        this.data = data;
        return this;
    }

    public AppResponse meta(MetaModel meta) {
        this.meta = meta;
        return this;
    }

    public MetaModel getMeta() {
        return meta;
    }

    public AppResponse header(Object data) {
        this.header = data;
        return this;
    }

    public Object getHeader() {
        return header;
    }
}

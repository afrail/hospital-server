package com.sopnobazz.demo.comon.base.response;

import java.util.Date;

import com.sopnobazz.demo.comon.base.model.MetaModel;


/**
 * @version 1.0.0
 * @Project hospital-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 23, 2022
 */


@SuppressWarnings("rawtypes")
public class FeignResponse<T> {

    private Date timestamp;
    private Integer status;
    private String message;
    private T body;
    private MetaModel meta;

    private FeignResponse(Integer status) {
        this.status = status;
        this.timestamp = new Date();
    }

    private FeignResponse() {
    }


    public static FeignResponse<?> build(Integer status) {
        return new FeignResponse(status);
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }


    public FeignResponse message(String message) {
        this.message = message;
        return this;
    }

    public T getBody() {
        return body;
    }

    public FeignResponse body(T data) {
        this.body = data;
        return this;
    }

    public FeignResponse meta(MetaModel meta) {
        this.meta = meta;
        return this;
    }

    public MetaModel getMeta() {
        return meta;
    }
}

package com.sopnobazz.demo.comon.response;

import java.io.Serializable;


import lombok.Data;

/**
 * @version 1.0.0
 * @Since June 23, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */

@Data
public class CommonResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean status;

    private String message;

    private String messageBn;

    private Object data;


}

package com.sopnobazz.demo.comon.base.dto;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


/**
 * @version 1.0.0
 * @Project hospital-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 23, 2022
 */


@Component
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoggedInUserDto {
    private Integer appUserId;
    private UUID userId;
    private Integer doctorId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String profileImg;
    private String partnerCode;

    private UUID roleId;
    private String roleName;

    private String token;

}

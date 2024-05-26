package com.sopnobazz.demo.comon.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @version 2.0.0
 * @Since MARCH 30, 2022
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */


@Data
@Entity
@Table(name = "SYA_APP_USER_LOG")
public class AppUserLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "OFFICE_ID")
    private Integer office;

    @Column(name = "APP_USER_ID")
    private Integer appUser;

    @Column(name = "APP_USER_EMPLOYEE_ID")
    private Integer appUserEmp;

    @Column(name = "LOGIN_DATE")
    private Date loginDate;

    @Column(name = "IS_REPORT", columnDefinition = "boolean default false")
    private Boolean reportIS = false;

}

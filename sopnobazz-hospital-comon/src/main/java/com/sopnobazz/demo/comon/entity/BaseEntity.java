package com.sopnobazz.demo.comon.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;


/**
 * @version 1.0.0
 * @Project demo-management
 * @Author Md. Nayeemul Islam
 * @Since May 28, 2021
 */

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 9132875688068247271L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ACTIVE", columnDefinition = "boolean default true")
    private Boolean active = true;

    @Column(name = "ENTRY_USER", updatable = false, nullable = false)
    private Integer entryUser;

    @Column(name = "ENTRY_DATE", updatable = false, nullable = false)
    private Date entryDate;

    @Column(name = "UPDATE_USER", insertable = false)
    private Integer updateUser;

    @Column(name = "UPDATE_DATE", insertable = false)
    private Date updateDate;

    @Column(name = "ENTRY_HOSPITAL_CODE", length = 50)
    private String entryHospitalCode;

    @Column(name = "UPDATE_HOSPITAL_CODE", length = 50)
    private String updateHospitalCode;
}

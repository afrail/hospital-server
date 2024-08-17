package com.sopnobazz.demo.comon.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @version 1.0.0
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @Since July 06, 2021
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SYA_APP_USER")
public class AppUser extends BaseEntity {


    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(AppUser.class);

    @Column(name = "USERNAME", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "HOSPITAL_CODE")
    private String hospitalCode;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "MOBILE", length = 20)
    private String mobile;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "BANGLA_NAME", length = 100)
    private String banglaName;

    @Column(name = "DESIGNATION", length = 100)
    private String designation;

    @Column(name = "IS_ACCOUNT_EXPIRED", columnDefinition = "boolean default false")
    private Boolean accountExpired = false;

    @Column(name = "IS_CREDENTIALS_EXPIRED", columnDefinition = "boolean default false")
    private Boolean credentialsExpired = false;

    @Column(name = "IS_ACCOUNT_LOCKED", columnDefinition = "boolean default false")
    private Boolean accountLocked = false;
}

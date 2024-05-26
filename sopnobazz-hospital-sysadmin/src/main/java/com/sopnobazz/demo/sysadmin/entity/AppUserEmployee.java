package com.sopnobazz.demo.sysadmin.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sopnobazz.demo.comon.entity.AppUser;
import com.sopnobazz.demo.comon.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @version 1.0.0
 * @Project hospital-management
 * @Author Md. Nayeemul Islam
 * @Since July 07, 2021
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SYA_APP_USER_EMPLOYEE")
public class AppUserEmployee extends BaseEntity {


    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(AppUserEmployee.class);

    @OneToOne
    @JoinColumn(name = "APP_USER_ID", nullable = false)
    private AppUser appUser;

    @Column(name = "EMPLOYEE_CODE", length = 50, nullable = false)
    private String employeeCode;

    @Column(name = "DISPLAY_NAME", nullable = false)
    private String displayName;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "BANGLA_NAME", length = 100, nullable = false)
    private String banglaName;

    @Column(name = "DESIGNATION", length = 100)
    private String designation;

    @Column(name = "MOBILE", length = 20)
    private String mobile;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "ACTIVE_DATE", nullable = false)
    private Date activeDate;

    @Column(name = "INACTIVE_DATE")
    private Date inactiveDate;

}

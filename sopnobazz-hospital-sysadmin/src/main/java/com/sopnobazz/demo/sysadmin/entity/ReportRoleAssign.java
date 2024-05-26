package com.sopnobazz.demo.sysadmin.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sopnobazz.demo.comon.entity.AppUser;
import com.sopnobazz.demo.comon.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SYS_REPORT_ROLE_ASSIGN")
public class ReportRoleAssign extends BaseEntity {

    private static final long serialVersionUID = 5824148595416692347L;

    @OneToOne
    @JoinColumn(name = "APP_USER_ID", nullable = false)
    private AppUser appUser;

    @OneToOne
    @JoinColumn(name = "REPORT_ROLE_ID", nullable = false)
    private ReportRole reportRole;

}

package com.sopnobazz.demo.sysadmin.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "SYS_REPORT_ROLE_PERMISSION")
public class ReportRolePermission extends BaseEntity {

    private static final long serialVersionUID = 5824148595416692347L;

    @ManyToOne
    @JoinColumn(name = "REPORT_ROLE_ID")
    private ReportRole reportRole;

    @OneToOne
    @JoinColumn(name = "REPORT_MASTER_ID")
    private ReportMaster report;

}

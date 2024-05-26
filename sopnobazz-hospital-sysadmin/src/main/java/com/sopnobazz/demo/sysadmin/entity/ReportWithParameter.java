package com.sopnobazz.demo.sysadmin.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Table(name = "SYS_REPORT_WITH_PARAMETER")
public class ReportWithParameter extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "REPORT_MASTER_ID", nullable = false)
    @ManyToOne(cascade = CascadeType.REFRESH)
    private ReportMaster reportMaster;

    @JoinColumn(name = "PARAMETER_MASTER_ID", nullable = true)
    @ManyToOne(cascade = CascadeType.REFRESH)
    private ParameterMaster parameterMaster;

    @Column(name = "SQL")
    private String sql;

    @Column(name = "SERIAL", nullable = false)
    private Integer serial;

    @Column(name = "REQUIRED")
    private Boolean required;

    @Transient
    private String dropdownListData;
}

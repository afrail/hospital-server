package com.sopnobazz.demo.sysadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "SYS_SUB_REPORT_MASTER")
public class SubReportMaster extends BaseEntity {

    private static final long serialVersionUID = 5824148595416692347L;

    @ManyToOne
    @JoinColumn(name = "REPORT_ID", nullable = false)
    private ReportMaster reportMaster;

    @ManyToOne
    @JoinColumn(name = "REPORT_UPLOAD_ID", nullable = false)
    private ReportUpload reportUpload;

    @Column(name = "SERIAL", nullable = false)
    private Integer serial;


}

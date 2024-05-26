package com.sopnobazz.demo.sysadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sopnobazz.demo.comon.entity.BaseEntity;
import com.sopnobazz.demo.comon.entity.MenuItem;

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
@Table(name = "SYS_REPORT_MASTER")
public class ReportMaster extends BaseEntity {

    private static final long serialVersionUID = 5824148595416692347L;

    @Column(name = "REPORT_TITLE", nullable = false)
    private String reportTitle;

    @ManyToOne
    @JoinColumn(name = "MODULE_ID", nullable = false)
    private MenuItem module;

    @ManyToOne
    @JoinColumn(name = "REPORT_UPLOAD_ID", nullable = false)
    private ReportUpload reportUpload;

    @Column(name = "SERIAL", nullable = false)
    private Integer serial;

}

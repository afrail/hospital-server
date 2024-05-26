package com.sopnobazz.demo.sysadmin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "SYS_REPORT_UPLOAD")
public class ReportUpload extends BaseEntity {

    private static final long serialVersionUID = 5824148595416692347L;

    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    @Column(name = "FILE_NAME", nullable = false, unique = true)
    private String fileName;

    @Column(name = "FILE_NAME_JASPER", nullable = false, unique = true)
    private String fileNameJasper;

    @Column(name = "FILE_LOCATION", nullable = false)
    private String fileLocation;

    @Column(name = "FILE_NAME_PARAMS")
    private String fileNameParams;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "IS_SUBREPORT")
    private Boolean isSubreport;

}

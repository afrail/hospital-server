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
@Table(name = "SYS_REPORT_ROLE")
public class ReportRole extends BaseEntity {

    private static final long serialVersionUID = 5824148595416692347L;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "BANGLA_NAME", nullable = false, unique = true)
    private String banglaName;

}

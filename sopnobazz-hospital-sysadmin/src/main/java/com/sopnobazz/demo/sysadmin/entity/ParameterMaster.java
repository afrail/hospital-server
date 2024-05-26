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
@Table(name = "SYS_PARAMETER_MASTER")
public class ParameterMaster extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "PARAMETER_TITLE", nullable = false)
    private String parameterTitle;

    @Column(name = "BANGLA_NAME", nullable = false)
    private String banglaName;

    @Column(name = "PARAMETER_NAME", nullable = false, unique = true)
    private String parameterName;

    @Column(name = "DATA_TYPE", nullable = false)
    private String dataType;

    @Column(name = "SQL", length = 600)
    private String sql;

}

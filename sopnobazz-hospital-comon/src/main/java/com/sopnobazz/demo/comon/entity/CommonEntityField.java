package com.sopnobazz.demo.comon.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @version 1.0.0
 * @Since June 01, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class CommonEntityField extends BaseEntity {


    private static final long serialVersionUID = 1L;

    @Column(name = "CODE", length = 10, nullable = false, unique = true)
    private String code;


}

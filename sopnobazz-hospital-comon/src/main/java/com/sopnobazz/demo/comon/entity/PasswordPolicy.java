package com.sopnobazz.demo.comon.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version 1.0.0
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @Project ibcs-bof-erp
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SYA_PASSWORD_POLICY")
public class PasswordPolicy extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @Column(name = "MIN_LENGTH", nullable = false)
    private Integer minLength;

    @Column(name = "SEQUENTIAL")
    private Boolean sequential;


    @Column(name = "SPECIAL_CHAR")
    private Boolean specialChar;


    @Column(name = "ALPHANUMERIC")
    private Boolean alphanumeric;


    @Column(name = "UPPER_LOWER")
    private Boolean upperLower;

    @Column(name = "MATCH_USERNAME")
    private Boolean matchUsername;


    @Column(name = "PASSWORD_REMEMBER")
    private Integer passwordRemember;


    @Column(name = "PASSWORD_AGE")
    private Integer passwordAge;


}

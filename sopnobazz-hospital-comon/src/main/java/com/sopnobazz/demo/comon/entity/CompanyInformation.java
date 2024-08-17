package com.sopnobazz.demo.comon.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @version 1.0.0
 * @Since August 16, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */


@Data
@Entity
@Table(name = "COMPANY_INFORMATION")
public class CompanyInformation extends BaseEntity {

    @Column(name = "COMPANY_ID")
    private String companyId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BANGLA_NAME")
    private String banglaName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "BANGLA_ADDRESS")
    private String banglaAddress;

    @Column(name = "LOGO_URL")
    private String logoUrl;

    @Column(name = "LOGO_NAME")
    private String logoName;
}

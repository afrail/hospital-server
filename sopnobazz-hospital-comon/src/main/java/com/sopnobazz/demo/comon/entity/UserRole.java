package com.sopnobazz.demo.comon.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @version 1.0.0
 * @Since July 06, 2021
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SYA_USER_ROLE")
public class UserRole extends CommonEntityField {

    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "BANGLA_NAME", length = 100, nullable = false, unique = true)
    private String banglaName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    List<UserRolePermission> rolePermissionList = new ArrayList<>();


}

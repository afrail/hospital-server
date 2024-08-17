package com.sopnobazz.demo.comon.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @version 2.0.0
 * @Since 4/26/2022
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SYA_USER_ROLE_ASSIGN_MASTER")
public class UserRoleAssignMaster extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "APP_USER_ID", nullable = false)
    private AppUser appUser;

}

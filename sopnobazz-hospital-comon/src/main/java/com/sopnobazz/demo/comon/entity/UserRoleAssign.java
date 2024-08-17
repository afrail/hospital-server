package com.sopnobazz.demo.comon.entity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @version 1.0.0
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @Since July 10, 2021
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SYA_USER_ROLE_ASSIGN", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"APP_USER_ID", "USER_ROLE_ID"})
})
public class UserRoleAssign extends BaseEntity {


    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(UserRoleAssign.class);

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    @JoinColumn(name = "MASTER_ID")
    private UserRoleAssignMaster master;

    @OneToOne
    @JoinColumn(name = "APP_USER_ID", nullable = false)
    private AppUser appUser;

    @OneToOne
    @JoinColumn(name = "USER_ROLE_ID", nullable = false)
    private UserRole userRole;

}

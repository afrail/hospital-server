package com.sopnobazz.demo.comon.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@Table(name = "SYA_PASSWORD_HISTORY")
public class PasswordHistory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(PasswordHistory.class);

    @OneToOne
    @JoinColumn(name = "APP_USER_ID", nullable = false)
    private AppUser appUser;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

}

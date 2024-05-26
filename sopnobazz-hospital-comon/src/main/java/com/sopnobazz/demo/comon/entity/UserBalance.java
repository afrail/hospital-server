package com.sopnobazz.demo.comon.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @version 1.0.0
 * @Project hospital-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 9, 2022
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SYA_USER_BALANCE")
public class UserBalance extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "APP_USER_ID", nullable = false, unique = true)
    private AppUser appUser;

    private Double money;

    private Integer point;


    @OneToOne
    @JoinColumn(name = "CURRENCY_ID", nullable = false)
    private CommonSetupDetails currency;

}

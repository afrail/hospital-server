/**
 *
 */
package com.sopnobazz.demo.sysadmin.doctor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sopnobazz.demo.comon.entity.AppUser;
import com.sopnobazz.demo.comon.entity.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 * @version 1.0.0
 */


@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SYA_DOCTOR")
public class Doctor extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "APP_USER_ID", nullable = false, unique = true)
    private AppUser appUser;

    private String hospital;

    private String designation;

    @Lob
    private String description;

    private Boolean agreements;


}

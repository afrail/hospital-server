/**
 *
 */
package com.sopnobazz.demo.sysadmin.doctor.degree;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sopnobazz.demo.comon.base.entity.BaseEntityUuid;
import com.sopnobazz.demo.comon.entity.AppUser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Project demo-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 23, 2022
 * @version 1.0.0
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SYA_DOCTOR_DEGREES")
public class DoctorDegrees extends BaseEntityUuid {

    @OneToOne
    @JoinColumn(name = "APP_USER_ID", nullable = false, unique = true)
    private AppUser appUser;

    private String duration;

    private String startMonth;

    private String startYear;

    private String endMonth;

    private String endYear;

    private String instituteName;

    private String location;

    private String specialization;

    @Lob
    private String description;

}

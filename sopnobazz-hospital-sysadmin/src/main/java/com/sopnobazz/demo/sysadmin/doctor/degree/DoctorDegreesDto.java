/**
 *
 */
package com.sopnobazz.demo.sysadmin.doctor.degree;

import java.util.UUID;

import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sopnobazz.demo.comon.base.dto.BaseDto;
import com.sopnobazz.demo.comon.entity.AppUser;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Project demo-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 24, 2022
 * @version 1.0.0
 */

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorDegreesDto extends BaseDto {

    private UUID id;

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

package com.sopnobazz.demo.doctor_patient.entity;


import com.sopnobazz.demo.comon.entity.AppUser;
import com.sopnobazz.demo.comon.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "EHM_DOCTOR_AND_STAFF_INFORMATION")
public class DoctorInformation extends BaseEntity {

    @Column(name = "IS_BOF_EMPLOYEE")
    private boolean bofEmployee;
    
    @OneToOne
	@JoinColumn(name = "APP_USER_ID", unique = true)
    private AppUser appUser;

    @Column(name = "STAFF_TYPE_ID")
    private Integer staffType;

    @Column(name = "NAME", length = 150, nullable = false)
    private String name;

    @Column(name = "BANGLA_NAME", length = 150)
    private String banglaName;

    @Column(name = "Doctor_Rank", length = 100)
    private String doctorRank;

    @Column(name = "MOBILE_NUMBER", length = 15)
    private String mobileNumber;

    @Column(name = "SPECIAL_FOR", columnDefinition = "text")
    private String specialFor;

    @Column(name = "SPECIAL_FOR_BANGLA", columnDefinition = "text")
    private String specialForBangla;
    
    @Column(name = "ROOM_NO", length = 50)
    private String roomNo;

    @Column(name = "ACTIVE_DATE")
    private Date activeDate;

    @Column(name = "INACTIVE_DATE")
    private Date inactiveDate;

    @Column(name = "PICTURE")
    private String picture;

    @Column(name="HOS_TYPE")
    private Integer hosType;

}

package com.sopnobazz.demo.doctor_patient.entity;

import com.sopnobazz.demo.comon.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * @Since Dec 21, 2021
 * @Author Debobrato Biswas - V00005
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "EHM_TOKEN_REGISTER")
public class TokenRegister extends BaseEntity {

	@Column(name = "VISIT_DATE")
    private Date visitDate;

	@Column(name = "TOKEN_NUMBER", length = 12)
    private String tokenNumber;

    @ManyToOne
    @JoinColumn(name = "PATIENT_INFO_ID")
    private PatientInfo patientInfo;

	/* doctor */
    @ManyToOne
    @JoinColumn(name = "REFER_TO_DOCTOR_ID")
    private DoctorInformation referToDoctorId;
    
    @Column(name = "REFER_TO_DOCTOR_NAME")
    private String referToDoctorName;
    
    @Column(name = "REFER_TO_DOCTOR_ROOM", length = 50)
    private String referToDoctorRoom;

    @Column(name = "PRIMARY_PROBLEM", length = 1000)
    private String primaryProblem;

	/* medicine */
    @Column(name = "PAT_PRES_MASTER_ID")
    private Integer prescriptionMasterId;

    @Column(name = "HOSPITAL_TYPE")
    private Integer hospitalType;
    
    @Column(name = "PAT_PRES_NO")
    private String prescriptionNo;

    @Column(name = "ACTION_TOKEN", columnDefinition = "boolean default false")
    private Boolean actionToken = false;

    @Column(name="PULSE", length = 20)
    private String pulse;

    @Column(name="BP", length = 20)
    private String bp;

    @Column(name="TEMP", length = 20)
    private String temp;

    @Column(name="HEIGHT", length = 20)
    private String height;

    @Column(name="WEIGHT", length = 20)
    private String weight;

    @Column(name="OFC", length = 20)
    private String ofc;

    @Column(name="SPO2", length = 20)
    private String spo2;

    @Column(name="IS_ABSENCE")
    private int absenceIs = 0;


}

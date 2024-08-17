package com.sopnobazz.demo.doctor_patient.request;


import com.sopnobazz.demo.comon.entity.BaseEntity;
import com.sopnobazz.demo.comon.entity.CommonSetupDetails;
import com.sopnobazz.demo.doctor_patient.entity.DoctorInformation;
import com.sopnobazz.demo.doctor_patient.entity.PatientInfo;
import lombok.Data;

import java.util.Date;


@Data
public class TokenRegisterRequest extends BaseEntity {

    private Integer patientId;
    private String patientName;
    private Date registrationDate;
    private String age;
    private String nationalId;
    private String identityMark;
    private String presentAddress;
    private String contactNo;
    private String email;
    private String picture;

    private Integer TokenId;
    private PatientInfo patientInfo;
    private Date visitDate;
    private String tokenNumber;
    private DoctorInformation referToDoctorId;
    private String referToDoctorName;
    private String referToDoctorRoom;
    private String primaryProblem;
    private Boolean actionToken = false;
    private String pulse;
    private String bp;
    private String temp;
    private String height;
    private String weight;
    private String ofc;
    private String spo2;
    private int absenceIs = 0;
}

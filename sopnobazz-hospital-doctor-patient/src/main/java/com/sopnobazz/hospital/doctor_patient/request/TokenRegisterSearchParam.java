package com.sopnobazz.hospital.doctor_patient.request;

import lombok.Data;

import java.util.Date;



@Data
public class TokenRegisterSearchParam {
    
	private Integer doctorId;
	private Date fromDate;
    private Date toDate;
    private String patientCode; 
    private Integer hosType;
    private Integer hospitalType;
    private Integer visitType;

}

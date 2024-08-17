package com.sopnobazz.demo.doctor_patient.request;

import java.util.Date;

import lombok.Data;

@Data
public class PatientPrescriptionSearchParam {
	 private Integer doctorId;
	 private Date fromDate;
	 private Date toDate;
	 private String patientCode; 
	 private Integer hosType;

}

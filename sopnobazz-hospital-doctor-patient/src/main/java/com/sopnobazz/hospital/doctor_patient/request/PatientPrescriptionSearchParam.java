package com.ibcsprimax.bof.employee_health.request;

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

package com.sopnobazz.demo.doctor_patient.request;

import lombok.Data;

import java.util.Date;



@Data
public class TokenRegisterSearchParam {
    
	private Integer doctorId;
	private Date fromDate;
    private Date toDate;

}

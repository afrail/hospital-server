package com.sopnobazz.hospital.doctor_patient.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sopnobazz.demo.comon.entity.BaseEntity;
import com.sopnobazz.demo.comon.entity.CommonSetupDetails;

import lombok.Data;

/**
 * @Since Dec 22, 2021
 * @Author Afrail Hossain - 591
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Data
@Entity
@Table(name = "EHM_PATIENT_ILLNESS_HISTORY")
public class PatientIllnessHistory extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name="PATIENT_INFO_ID", nullable = false)
	private TokenRegister patientInfo;
	
	@Column(name="FROM_DATE")
	private Date fromDate;
	
	@Column(name="TO_DATE")
	private Date toDate;
	
	@ManyToOne
	@JoinColumn(name="ILLNESS_NAME_ID")
	private CommonSetupDetails illnessName;
	
	@Column(name="DISPOSAL", length = 256)
	private String disposal;
	
	

}

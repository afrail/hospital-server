package com.sopnobazz.hospital.doctor_patient.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.sopnobazz.demo.comon.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Since April 24, 2021
 * @Author Md Mizanur Rahman -598
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "EHM_PATIENT_ENVESTIGATION_REPORT")
public class PatientInvestigationReport extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JoinColumn(name="PATIENT_ID", nullable = false)
    private TokenRegister patient;
	
	@Column(name = "INV_DATE", nullable = false)
	private Date invDate;
	
	@Column(name = "REMARKS")
	private String remarks;

}

package com.sopnobazz.hospital.doctor_patient.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.sopnobazz.demo.comon.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Since Dec 23, 2021
 * @Author Afrail Hossain - 591
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "EHM_PAT_PRES_MASTER")
public class PatientPrescriptionMaster extends BaseEntity {
	
	
	@Column(name="PRESCRIPTION_NO", length = 20)
	private String prescriptionNo;
	
	@Column(name="TOKEN_REGISTER_ID")
	private Integer tokenRegisterId;
	
	@Column(name="TOKEN_REGISTER_NO")
	private String tokenRegisterNo;
	
	/*@ManyToOne
	@JoinColumn(name="PATIENT_INFO_ID")
	private PatientInfo patientInfo;
	
	@ManyToOne
	@JoinColumn(name="DOCTOR_INFO_ID")
	private DoctorAndStaffInformation doctorInfo;*/
	
    
    @Column(name="ILLNESS")
	private String illness;
	
	@Column(name="PULSE", length = 20)
	private String pulse;
	
	@Column(name="BP", length = 20)
	private String bp;
	
	@Column(name="TEMP", length = 20)
	private String temp;
	
	@Column(name="RR", length = 100)
	private String rr;
	
	@Column(name="HEIGHT", length = 20)
	private String height;
	
	@Column(name="WEIGHT", length = 20)
	private String weight;
	
	@Column(name="OFC", length = 20)
	private String ofc;

	@Column(name="SPO2", length = 20)
	private String spo2;
	
	
	@Column(name="NOTE", length = 1000)
	private String note;
	
	@Column(name="PRESCRIPTION_DATE")
	private Date prescriptionDate;
	
	@Column(name="OTHERS_ADVICE", length = 1000)
	private String otherAdvice;
	
	@Column(name="FOLLOW_UP_VISIT_DURATION")
	private Integer followUpVisitDuration;
	
	@Column(name="FOLLOW_UP_VISIT_DATE")
	private Date followUpVisitDate;
	
	@Column(name="FOLLOW_UP_COMMENT", length = 1000)
	private String followUpComment;
	
	@Column(name = "IS_INVESTIGATION_ENTRY", columnDefinition = "boolean default false")
	private Boolean investigationEntryIs = false;
	
	@Column(name="DISPOSAL_ONLY_ID")
	private Integer disposalId;
	
	@Column(name="DISPOSAL_NAME", length = 100)
	private String disposalName;
	
	@Column(name="DISPOSAL_DURATION")
	private Integer disposalDuration;
	
	@Column(name="DISPOSAL_DWMY")
	private Integer disposalDwmy;
	
	@Column(name="DISPOSAL_DATE")
	private Date disposalDate;
	
	@Column(name="REFERRED_DOCTOR_ID")
	private Integer referredDoctorId;
	
//	@Column(name="REFERRED_DOCTOR")
//	private String referredDoctor;
	
	@Column(name="HOS_TYPE")  // 1 = hospital , 2 = dental, 3 = emergency
	private Integer hosType;
	
	@Column(name = "EMPLOYEE_CODE", length=50)
	private String employeeCode;
	
	@Column(name = "ON_Examination", length=1000)
	private String onExamination;
	
	
	/* for template */
	@Column(name = "IS_TEMPLATE", columnDefinition = "boolean default false")
	private Boolean templateIs = false;
	
	@Column(name = "TEMPLATE_NAME", length=150)
	private String templateName;

	@Column(name = "IS_TREATMENT", columnDefinition = "boolean default false")
	private Boolean treatmentIs = false;
	
}

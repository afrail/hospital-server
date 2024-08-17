package com.sopnobazz.demo.doctor_patient.entity;

import com.sopnobazz.demo.comon.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;


@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "EHM_PATIENT_INFO")
public class PatientInfo extends BaseEntity {

	@Column(name="PATIENT_NAME", length = 150)
	private String patientName;

	@Column(name="REGISTRATION_DATE")
	private Date registrationDate;
	
	@Column(name="AGE")
	private String age;

	@Column(name="NATIONAL_ID", length = 50)
	private String nationalId;

	@Column(name="IDENTITY_MARK", length = 200)
	private String identityMark;

	@Column(name="PRESENTADDRESS", length = 250)
	private String presentAddress;

	@Column(name="CONTACT_NO", length = 15)
	private String contactNo;
	
	@Column(name="EMAIL", length = 50)
	private String email;

	@Column(name="PICTURE", length = 150)
	private String picture;

}

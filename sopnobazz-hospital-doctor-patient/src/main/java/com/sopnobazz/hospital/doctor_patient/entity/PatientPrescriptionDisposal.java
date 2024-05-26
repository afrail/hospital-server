package com.sopnobazz.hospital.doctor_patient.entity;

import com.sopnobazz.demo.comon.entity.BaseEntity;
import com.sopnobazz.demo.comon.entity.CommonSetupDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @Since SEP 27, 2022
 * @Author Afrail Hossain -591
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "PAT_PRES_DISPOSAL")
public class PatientPrescriptionDisposal extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
    @JoinColumn(name = "PAT_PRES_MASTER_ID")
    private PatientPrescriptionMaster prescriptionMaster;
    
	@ManyToOne
	@JoinColumn(name="DISPOSAL_ID")
	private CommonSetupDetails disposal;
	
	@Column(name="DISPOSAL_NAME", length = 100)
	private String disposalName;
	
	@Column(name="DISPOSAL_DURATION")
	private Integer disposalDuration;
	
	@Column(name="DISPOSAL_DWMY")
	private Integer disposalDwmy;
	
	@Column(name="DISPOSAL_DATE")
	private Date disposalDate;
}

package com.sopnobazz.hospital.doctor_patient.entity;


import com.sopnobazz.demo.comon.entity.BaseEntity;
import com.sopnobazz.demo.comon.entity.CommonSetupDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * @Since Dec 26, 2021
 * @Author Md. Chabed Alam - 601
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "EHM_PAT_PRES_INVESTIGATION_FINDING")
public class PatientPrescriptionInvestigationFinding extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
    @JoinColumn(name = "PAT_PRES_MASTER_ID")
    private PatientPrescriptionMaster prescriptionMaster;

    @ManyToOne
    @JoinColumn(name = "INVESTIGATION_ID")
    private CommonSetupDetails investigation;

    @Column(name = "INVESTIGATION")
    private String investigationValue;
    
    @Column(name = "FINDING")
    private String finding;


}

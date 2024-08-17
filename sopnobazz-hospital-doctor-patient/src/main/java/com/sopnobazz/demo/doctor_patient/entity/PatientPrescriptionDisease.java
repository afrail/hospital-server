package com.sopnobazz.demo.doctor_patient.entity;

import com.sopnobazz.demo.comon.entity.BaseEntity;
import com.sopnobazz.demo.comon.entity.CommonSetupDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;



@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "EHM_PAT_PRES_DISEASE")
public class PatientPrescriptionDisease extends BaseEntity {


	private static final long serialVersionUID = 1L;

	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
    @JoinColumn(name = "PAT_PRES_MASTER_ID")
    private PatientPrescriptionMaster prescriptionMaster;

    @ManyToOne
    @JoinColumn(name = "DISEASE_ID")
    private EhmCommonLookupDetails disease;

    @Column(name = "ON_EXAMINATION")
    private String onExamination;
    
}

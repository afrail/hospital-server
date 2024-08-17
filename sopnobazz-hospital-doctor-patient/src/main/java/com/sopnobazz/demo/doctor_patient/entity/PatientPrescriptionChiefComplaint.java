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
@Table(name = "PAT_PRES_CHIEF_COMPLAINT")
public class PatientPrescriptionChiefComplaint extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
    @OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
    @JoinColumn(name = "PAT_PRES_MASTER_ID")
    private PatientPrescriptionMaster prescriptionMaster;

    @ManyToOne
    @JoinColumn(name = "CHIEF_COMPLAINT_ID")
    private EhmCommonLookupDetails chiefComplaint;

    @Column(name = "CHIEF_COMPLAINT")
    private String chiefComplaintValue;
    
    @Column(name = "DURATION")
    private Integer duration;
    
    @Column(name = "DWMY")
    private Integer dwmy;
    
}

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
@Table(name = "EHM_PAT_PRES_TREATMENT")
public class PatientPrescriptionTreatment extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "SERIAL_NO")
    private Integer serialNo;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
    @JoinColumn(name = "PAT_PRES_MASTER_ID")
    private PatientPrescriptionMaster prescriptionMaster;

    @ManyToOne
    @JoinColumn(name = "MEDICINE_MASTER_ID")
    private MedicineMaster medicineMaster;

    @ManyToOne
    @JoinColumn(name = "DOSE_ID")
    private CommonSetupDetails dose;
    
    @Column(name = "DOSE")
    private String doseValue;
    
    @Column(name = "DURATION")
    private Integer duration;
    
    @Column(name = "DWMY")
    private Integer dwmy;

    @Column(name = "QTY")
    private Integer qty;
    
    @ManyToOne
    @JoinColumn(name = "INSTRUCTION_ID")
    private CommonSetupDetails instruction;
    
    @Column(name = "INSTRUCTION")
    private String instructionValue; 

    @Column(name = "IS_CONTINUE", columnDefinition = "boolean default false")
	private Boolean continueIs = false;
    
    @Column(name = "DELIVERY_QTY", columnDefinition = "integer default 0")
    private Integer deliveryQty;
    

}

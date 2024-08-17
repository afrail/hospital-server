package com.sopnobazz.demo.doctor_patient.entity;


import com.sopnobazz.demo.comon.entity.BaseEntity;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "ON_EXAMINATION")
@Data
public class OnExamination extends BaseEntity {

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "PAT_PRES_MASTER_ID")
    private PatientPrescriptionMaster prescriptionMaster;

    @Column(name = "ON_EXAMINATION")
    private String onExamination;

    @Column(name="FINDING", length = 256)
    private String finding;
}

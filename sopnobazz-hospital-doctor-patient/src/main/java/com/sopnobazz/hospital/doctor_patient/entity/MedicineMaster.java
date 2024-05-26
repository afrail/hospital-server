package com.sopnobazz.hospital.doctor_patient.entity;

import com.sopnobazz.demo.comon.entity.BaseEntity;
import com.sopnobazz.demo.comon.entity.CommonSetupDetails;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @version 2.0.0
 * @Since Dec 07, 2021
 * @Author Debobrato Biswas - V00005
 * @Project ibcs-bof-erp
 */

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "MEDICINE_MASTER", indexes = {
    @Index(name = "I_EHM_MM_MEDICINE_GROUP_ID", columnList = "MEDICINE_GROUP_ID"),
    @Index(name = "I_EHM_MM_UNIT_MEASUREMENT" , columnList = "UNIT_MEASUREMENT" ),
    @Index(name = "I_EHM_MM_MEDICINE_NAME"    , columnList = "MEDICINE_NAME"    )
})
public class MedicineMaster extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "MEDICINE_GROUP_ID")
    private CommonSetupDetails medicineGroup;

    @Column(name = "MEDICINE_NAME", length = 200)
    private String medicineName;

    @Column(name = "MINIMUM_STOCK")
    private int minimumStock;

    @Column(name = "WARNING_STOCK")
    private int warningStock;

    @Column(name = "AVAILABLE_STOCK")
    private int availableStock;




}

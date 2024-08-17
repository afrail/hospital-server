package com.sopnobazz.demo.doctor_patient.entity;

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
@Table(name = "MEDICINE_MASTER")
public class MedicineMaster extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "MEDICINE_GROUP_ID")
    private EhmCommonLookupDetails medicineGroup;

    @Column(name = "MEDICINE_NAME", length = 200)
    private String name;

    @Column(name = "MINIMUM_STOCK")
    private int minimumStock;

    @Column(name = "WARNING_STOCK")
    private int warningStock;

    @Column(name = "AVAILABLE_STOCK")
    private int availableStock;




}

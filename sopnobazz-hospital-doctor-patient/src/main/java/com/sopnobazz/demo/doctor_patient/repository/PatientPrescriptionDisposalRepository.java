package com.sopnobazz.demo.doctor_patient.repository;

import com.sopnobazz.demo.doctor_patient.entity.PatientPrescriptionDisposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Since SEP 27, 2022
 * @Author Afrail Hossain -591
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Repository
public interface PatientPrescriptionDisposalRepository extends JpaRepository<PatientPrescriptionDisposal, Integer> {
    
	List<PatientPrescriptionDisposal> findByActive(boolean active);
    
    List<PatientPrescriptionDisposal> findByPrescriptionMasterId(Integer prescriptionMasterId);

    long deleteByPrescriptionMasterId(Integer prescriptionMasterId);
}


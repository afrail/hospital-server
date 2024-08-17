package com.sopnobazz.demo.doctor_patient.repository;

import com.sopnobazz.demo.doctor_patient.entity.PatientPrescriptionAdvice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Since Dec 26, 2021
 * @Author Md. Chabed Alam - 601
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Repository
public interface PatientPrescriptionAdviceRepository extends JpaRepository<PatientPrescriptionAdvice, Integer> {
    
	List<PatientPrescriptionAdvice> findByActive(boolean active);
    
    List<PatientPrescriptionAdvice> findByPrescriptionMasterId(Integer prescriptionMasterId);

    long deleteByPrescriptionMasterId(Integer prescriptionMasterId);
}


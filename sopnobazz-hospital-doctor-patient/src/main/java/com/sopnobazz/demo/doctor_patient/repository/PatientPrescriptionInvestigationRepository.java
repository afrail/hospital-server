package com.sopnobazz.demo.doctor_patient.repository;

import com.sopnobazz.demo.doctor_patient.entity.PatientPrescriptionInvestigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface PatientPrescriptionInvestigationRepository extends JpaRepository<PatientPrescriptionInvestigation, Integer> {
    
	List<PatientPrescriptionInvestigation> findByActive(boolean active);
    
    List<PatientPrescriptionInvestigation> findByPrescriptionMasterId(Integer prescriptionMasterId);

    long deleteByPrescriptionMasterId(Integer prescriptionMasterId);
}


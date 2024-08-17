package com.sopnobazz.demo.doctor_patient.repository;


import com.sopnobazz.demo.doctor_patient.entity.PatientPrescriptionInvestigationFinding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface PatientPrescriptionInvestigationFindingRepository extends JpaRepository<PatientPrescriptionInvestigationFinding, Integer> {
    
	List<PatientPrescriptionInvestigationFinding> findByActive(boolean active);
    
    List<PatientPrescriptionInvestigationFinding> findByPrescriptionMasterId(Integer prescriptionMasterId);

    long deleteByPrescriptionMasterId(Integer prescriptionMasterId);
}


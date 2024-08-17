package com.sopnobazz.demo.doctor_patient.repository;


import com.sopnobazz.demo.doctor_patient.entity.PatientIllnessHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientIllnessHistoryRepository extends JpaRepository<PatientIllnessHistory, Integer> {

    List<PatientIllnessHistory> findByActive(boolean b);
    List<PatientIllnessHistory> findByPatientInfoId(int searchId);
}

package com.sopnobazz.demo.doctor_patient.repository;

import com.sopnobazz.demo.doctor_patient.entity.OnExamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OnExaminationRepository extends JpaRepository<OnExamination, Long> {
    List<OnExamination> findByActive(boolean active);

    List<OnExamination> findByPrescriptionMasterId(Integer prescriptionMasterId);

    void deleteByPrescriptionMasterId(Integer prescriptionMasterId);
}

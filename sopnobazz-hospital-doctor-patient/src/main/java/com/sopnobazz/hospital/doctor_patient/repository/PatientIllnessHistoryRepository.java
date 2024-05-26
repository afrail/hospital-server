package com.ibcsprimax.bof.employee_health.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibcsprimax.bof.employee_health.entity.PatientIllnessHistory;

@Repository
public interface PatientIllnessHistoryRepository extends JpaRepository<PatientIllnessHistory, Integer>{
	
 List<PatientIllnessHistory> findByActive(boolean b);
 List<PatientIllnessHistory> findByPatientInfoId(int searchId);
}

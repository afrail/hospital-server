package com.ibcsprimax.bof.employee_health.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ibcsprimax.bof.employee_health.entity.PatientIllnessHistory;


public interface PatientIllnessHistoryService {
	
	/*CURD*/
	PatientIllnessHistory save(PatientIllnessHistory entity);

	PatientIllnessHistory update(PatientIllnessHistory entity);

	PatientIllnessHistory delete(PatientIllnessHistory entity);

    List<PatientIllnessHistory> getAll();

    List<PatientIllnessHistory> getAllActive();

    Page<PatientIllnessHistory> getPageableList(int page, int size);
    
    List<PatientIllnessHistory> getByPatId (Integer patId);

    /*BUSINESS*/
}

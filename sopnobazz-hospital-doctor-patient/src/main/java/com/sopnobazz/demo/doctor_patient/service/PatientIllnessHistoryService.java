package com.sopnobazz.demo.doctor_patient.service;



import com.sopnobazz.demo.doctor_patient.entity.PatientIllnessHistory;
import org.springframework.data.domain.Page;

import java.util.List;


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

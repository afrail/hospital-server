/**
 * 
 */
package com.sopnobazz.hospital.doctor_patient.service;

import java.util.List;

import com.sopnobazz.hospital.doctor_patient.entity.PatientInvestigationReport;
import org.springframework.data.domain.Page;



public interface PatientInvestigationReportService {
	

	//CRUD
	PatientInvestigationReport save(PatientInvestigationReport obj);
	PatientInvestigationReport update(PatientInvestigationReport obj);
	PatientInvestigationReport delete(PatientInvestigationReport obj);
	List<PatientInvestigationReport> getAll();
	List<PatientInvestigationReport> getAllActive();
	Page<PatientInvestigationReport> getPageableList(int page, int size);
	

}

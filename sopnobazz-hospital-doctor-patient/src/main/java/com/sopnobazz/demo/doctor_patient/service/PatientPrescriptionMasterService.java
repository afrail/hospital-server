package com.sopnobazz.demo.doctor_patient.service;

import java.util.List;

import com.sopnobazz.demo.doctor_patient.entity.PatientPrescriptionMaster;
import com.sopnobazz.demo.doctor_patient.entity.PatientPrescriptionTreatment;
import com.sopnobazz.demo.doctor_patient.request.PatientPrescriptionRequest;
import com.sopnobazz.demo.doctor_patient.request.PatientPrescriptionSearchParam;
import org.springframework.data.domain.Page;



public interface PatientPrescriptionMasterService {
	
	/*CURD*/
	PatientPrescriptionRequest save(PatientPrescriptionRequest entity, int userId);

	PatientPrescriptionRequest update(PatientPrescriptionRequest entity, int userId);

	PatientPrescriptionRequest delete(PatientPrescriptionRequest entity);

    List<PatientPrescriptionRequest> getAll();

    List<PatientPrescriptionRequest> getAllActive();

    Page<PatientPrescriptionMaster> getPageableList(int page, int size);
    List<PatientPrescriptionMaster> search(PatientPrescriptionSearchParam searchParam);

    /*BUSINESS*/
    List<PatientPrescriptionRequest> getPatient(Integer patient);
    List<PatientPrescriptionRequest> getTemplateList();
    PatientPrescriptionRequest convertToResponseType(PatientPrescriptionMaster master);


}

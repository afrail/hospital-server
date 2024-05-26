package com.sopnobazz.hospital.doctor_patient.service;

import java.util.List;

import com.sopnobazz.hospital.doctor_patient.entity.PatientPrescriptionMaster;
import com.sopnobazz.hospital.doctor_patient.entity.PatientPrescriptionTreatment;
import com.sopnobazz.hospital.doctor_patient.request.PatientPrescriptionRequest;
import org.springframework.data.domain.Page;



public interface PatientPrescriptionMasterService {
	
	/*CURD*/
	PatientPrescriptionRequest save(PatientPrescriptionRequest entity, int userId);

	PatientPrescriptionRequest update(PatientPrescriptionRequest entity, int userId);

	PatientPrescriptionRequest delete(PatientPrescriptionRequest entity);

    List<PatientPrescriptionRequest> getAll();

    List<PatientPrescriptionRequest> getAllActive();

    Page<PatientPrescriptionRequest> getPageableList(int page, int size);
    Page<PatientPrescriptionMaster> getPageableListWithHosType(int page, int size, int hosType);
    


    /*BUSINESS*/
    List<PatientPrescriptionRequest> getPatient(Integer patient);
    List<PatientPrescriptionRequest> getTemplateList(Integer hosType, int a);
    List<PatientPrescriptionTreatment> getContinueMedicine(Integer patId);
    List<PatientPrescriptionRequest> getChronicPatientLastPrescription(Integer patient);
    List<PatientPrescriptionRequest> getByPrescriptionNo(String prescriptionNo);
    PatientPrescriptionRequest convertToResponseType(PatientPrescriptionMaster master);


}

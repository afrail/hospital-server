package com.sopnobazz.hospital.doctor_patient.request;




import com.sopnobazz.hospital.doctor_patient.entity.*;
import lombok.Data;

import java.util.List;

/**
 * @Since Jan 04, 2022
 * @Author Md. Chabed Alam - 601
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Data
public class PatientPrescriptionRequest {
	
    private PatientPrescriptionMaster master;
    private List<PatientIllnessHistory> pastIllHistoryList;
    private List<PatientPrescriptionChiefComplaint> ccList;
    private List<PatientPrescriptionInvestigationFinding> investigationFindingList;
    private List<PatientPrescriptionDisease> diseaseList;
    private List<PatientPrescriptionInvestigation> investigationList;
    private List<PatientPrescriptionTreatment> treatmentList;
    private List<PatientPrescriptionAdvice> adviceList;
    private List<PatientPrescriptionDisposal> disposalList;
    private List<OnExamination> onExaminationList;
}

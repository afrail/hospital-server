package com.sopnobazz.hospital.doctor_patient.request;


import com.sopnobazz.hospital.doctor_patient.entity.PatientPrescriptionTreatment;
import com.sopnobazz.hospital.doctor_patient.entity.TokenRegister;
import lombok.Data;
import java.util.List;


@Data
public class TokenRegisterRequest {
    private TokenRegister master;
    private List<PatientPrescriptionTreatment> treatmentList;
}

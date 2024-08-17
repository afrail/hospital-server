package com.sopnobazz.demo.doctor_patient.service;

import com.sopnobazz.demo.doctor_patient.entity.DoctorInformation;
import org.springframework.data.domain.Page;

import java.util.List;


public interface DoctorInformationService {

    /*CURD*/
    DoctorInformation save(DoctorInformation doctorAndStaffInformation);

    DoctorInformation update(DoctorInformation doctorAndStaffInformation);

    DoctorInformation delete(DoctorInformation doctorAndStaffInformation);

    List<DoctorInformation> getAll();

    List<DoctorInformation> getAllActive();

    Page<DoctorInformation> getPageableList(int page, int size);

    DoctorInformation getByAppUserId(Integer appUser);
}

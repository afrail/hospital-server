package com.sopnobazz.hospital.doctor_patient.service;


import com.ibcsprimax.bof.employee_health.utils.EHMConstant;

import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.hospital.doctor_patient.entity.MedicineMaster;
import com.sopnobazz.hospital.doctor_patient.entity.PatientPrescriptionTreatment;
import com.sopnobazz.hospital.doctor_patient.entity.TokenRegister;
import com.sopnobazz.hospital.doctor_patient.repository.PatientPrescriptionTreatmentRepository;
import com.sopnobazz.hospital.doctor_patient.repository.TokenRegisterRepository;
import com.sopnobazz.hospital.doctor_patient.request.TokenRegisterRequest;
import lombok.AllArgsConstructor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class TokenRegisterServiceImpl implements TokenRegisterService {

    private TokenRegisterRepository repo;
    private PatientPrescriptionTreatmentRepository treatmentRepo;
    private final CommonUtils commonUtils;
    

    @Transactional
    @Override
    public TokenRegister save(TokenRegister body) {

        commonUtils.setEntryUserInfo(body);
        return repo.save(body);
    }
    
    @Transactional
    @Override
    public TokenRegister update(TokenRegister entity) {

        TokenRegister dbEntity = repo.findById(entity.getId()).get();
        commonUtils.setUpdateUserInfo(entity, dbEntity);
        return repo.save(entity);
    }


    @Transactional
    @Override
    public TokenRegister delete(TokenRegister entity) {
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<TokenRegister> getAll() {
        return repo.findAll();
    }

    @Override
    public List<TokenRegister> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<TokenRegister> getPageableList(int page, int size) {
        return null;
    }



}

package com.sopnobazz.demo.doctor_patient.service;


import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.doctor_patient.entity.PatientInfo;
import com.sopnobazz.demo.doctor_patient.entity.TokenRegister;
import com.sopnobazz.demo.doctor_patient.repository.PatientInfoRepository;
import com.sopnobazz.demo.doctor_patient.repository.TokenRegisterRepository;
import com.sopnobazz.demo.doctor_patient.request.TokenRegisterRequest;
import com.sopnobazz.demo.doctor_patient.request.TokenRegisterSearchParam;
import lombok.AllArgsConstructor;


import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class TokenRegisterServiceImpl implements TokenRegisterService {

    private TokenRegisterRepository repo;
    private final CommonUtils commonUtils;
    private PatientInfoRepository patientInfoRepository;
    

    @Transactional
    @Override
    public TokenRegister save(TokenRegisterRequest body) {

        commonUtils.setEntryUserInfo(body);
        if (!ObjectUtils.isEmpty(body.getPatientInfo()))
        {
            TokenRegister tokenRegister = new TokenRegister();
            BeanUtils.copyProperties(body, tokenRegister);
            tokenRegister.setTokenNumber(tokenGenerateNumber(tokenRegister));
            return repo.save(tokenRegister);
        }
        else {
            PatientInfo patientInfo = new PatientInfo();
            BeanUtils.copyProperties(body, patientInfo);
            PatientInfo savePatientInfo = patientInfoRepository.save(patientInfo);

            TokenRegister tokenRegister = new TokenRegister();
            BeanUtils.copyProperties(body, tokenRegister);
            tokenRegister.setTokenNumber(tokenGenerateNumber(tokenRegister));
            tokenRegister.setPatientInfo(savePatientInfo);
            return repo.save(tokenRegister);
        }
    }
    
    @Transactional
    @Override
    public TokenRegister update(TokenRegisterRequest body) {

            PatientInfo patientInfo = new PatientInfo();
            BeanUtils.copyProperties(body, patientInfo);
            patientInfo.setId(body.getPatientId());
            PatientInfo savePatientInfo = patientInfoRepository.findById(patientInfo.getId()).get();
            commonUtils.setUpdateUserInfo(patientInfo,savePatientInfo);
            patientInfoRepository.save(patientInfo);
            TokenRegister tokenRegister = new TokenRegister();
            BeanUtils.copyProperties(body, tokenRegister);
            tokenRegister.setId(body.getTokenId());
            TokenRegister token = repo.findById(tokenRegister.getId()).get();
            token.setTokenNumber(tokenGenerateNumber(token));
            commonUtils.setUpdateUserInfo(tokenRegister,token);
            return repo.save(tokenRegister);
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
    public PatientInfo getPatient(String phone) {
        return patientInfoRepository.findByContactNo(phone);
    }

    @Override
    public List<TokenRegister> getByAppUserId(Integer appUserId) {
        return repo.getTokenByDoctor(appUserId);
    }

    @Override
    public Page<TokenRegisterRequest>  getPageableList(int page, int size) {

        PageRequest pageRequest  = commonUtils.getPageRequest(page, size);
        Page<TokenRegister> pageResult = repo.findAll(pageRequest);
        List<TokenRegister> objList = pageResult.stream().collect(Collectors.toList());
        return new PageImpl<>(getWithPatientInfo(objList), pageRequest, pageResult.getTotalElements());
    }

    @Override
    public List<TokenRegisterRequest> getSearch(TokenRegisterSearchParam obj) {
        return getWithPatientInfo(
        repo.getBySearchParam(obj.getDoctorId(),obj.getFromDate(),obj.getToDate()));
    }


    private List<TokenRegisterRequest> getWithPatientInfo(List<TokenRegister> token){
        List<TokenRegisterRequest> tokenRegisterList = new ArrayList<>();
        for(TokenRegister tokenRegister: token){
            TokenRegisterRequest request = new TokenRegisterRequest();
            BeanUtils.copyProperties(tokenRegister, request);
            request.setTokenId(tokenRegister.getId());
            PatientInfo patientInfo = patientInfoRepository.findById(tokenRegister.getPatientInfo().getId()).get();
            BeanUtils.copyProperties(patientInfo, request);
            request.setPatientId(patientInfo.getId());
            tokenRegisterList.add(request);
        }
        return tokenRegisterList;
    }

    private String tokenGenerateNumber(TokenRegister entity){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyyyy-");
        LocalDateTime now = LocalDateTime.now();
        String datePart = dtf.format(now);
        int docId = entity.getReferToDoctorId().getId();
        int todayCount = repo.countTodayRegister(entity.getVisitDate(),docId) + 1;
        return  todayCount+"";
    }

}

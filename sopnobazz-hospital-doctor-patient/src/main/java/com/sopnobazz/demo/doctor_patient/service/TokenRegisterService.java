package com.sopnobazz.demo.doctor_patient.service;
import com.sopnobazz.demo.doctor_patient.entity.PatientInfo;
import com.sopnobazz.demo.doctor_patient.entity.TokenRegister;
import com.sopnobazz.demo.doctor_patient.request.TokenRegisterRequest;
import com.sopnobazz.demo.doctor_patient.request.TokenRegisterSearchParam;
import org.springframework.data.domain.Page;



import java.util.List;

public interface TokenRegisterService {
    TokenRegister save(TokenRegisterRequest body);

    TokenRegister update(TokenRegisterRequest body);

    TokenRegister delete(TokenRegister entity);

    List<TokenRegister> getAll();

    List<TokenRegister> getAllActive();
    PatientInfo getPatient(String phone);

    List<TokenRegister> getByAppUserId(Integer appUserId);

    Page<TokenRegisterRequest>  getPageableList(int page, int size);

    List<TokenRegisterRequest>  getSearch(TokenRegisterSearchParam obj);
}

package com.sopnobazz.hospital.doctor_patient.service;
import com.sopnobazz.hospital.doctor_patient.entity.TokenRegister;
import org.springframework.data.domain.Page;



import java.util.List;

public interface TokenRegisterService {
    TokenRegister save(TokenRegister body);

    TokenRegister update(TokenRegister body);

    TokenRegister delete(TokenRegister entity);

    List<TokenRegister> getAll();

    List<TokenRegister> getAllActive();

    Page<TokenRegister> getPageableList(int page, int size);

}

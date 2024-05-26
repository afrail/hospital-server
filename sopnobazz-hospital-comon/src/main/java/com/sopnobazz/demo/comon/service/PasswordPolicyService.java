package com.sopnobazz.demo.comon.service;

import java.util.List;

import com.sopnobazz.demo.comon.entity.PasswordPolicy;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Since July 05, 2021
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */
public interface PasswordPolicyService {

    //CRUD function
    PasswordPolicy save(PasswordPolicy PasswordPolicy);

    PasswordPolicy update(PasswordPolicy PasswordPolicy);

    PasswordPolicy delete(PasswordPolicy PasswordPolicy);

    List<PasswordPolicy> getAll();

    List<PasswordPolicy> getAllActive();

    Page<PasswordPolicy> getPageableList(int page, int size);


    //business function
}

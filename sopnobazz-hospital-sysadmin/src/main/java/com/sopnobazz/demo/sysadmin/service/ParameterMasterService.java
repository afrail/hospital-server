package com.sopnobazz.demo.sysadmin.service;

import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.ParameterMaster;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public interface ParameterMasterService {

    //CRUD
    ParameterMaster save(ParameterMaster obj);

    ParameterMaster update(ParameterMaster obj);

    ParameterMaster delete(ParameterMaster obj);

    List<ParameterMaster> getAll();

    List<ParameterMaster> getAllActive();

    Page<ParameterMaster> getPageableList(int page, int size);

    //Business
}

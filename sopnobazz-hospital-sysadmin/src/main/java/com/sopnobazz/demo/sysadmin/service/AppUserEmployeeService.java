package com.sopnobazz.demo.sysadmin.service;

import java.util.List;

import com.sopnobazz.demo.sysadmin.dto.AppUserEmployeeDto;
import com.sopnobazz.demo.sysadmin.entity.AppUserEmployee;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public interface AppUserEmployeeService {

    //CRUD function
    AppUserEmployee save(AppUserEmployeeDto AppUserEmployee);

    AppUserEmployee update(AppUserEmployeeDto AppUserEmployee);

    AppUserEmployee delete(AppUserEmployee AppUserEmployee);

    List<AppUserEmployee> getAll();

    List<AppUserEmployee> getAllActive();

    Page<AppUserEmployee> getPageableList(int page, int size);


    //business function
    AppUserEmployee getByAppUserId(Integer appUserId);

    List<AppUserEmployee> getByTransactionIdAndTransactionTable(Integer transactionId, String transactionTable);
//    void updateAppuserInfoByActiveEmpUser(AppUserEmployee savedEntity, AppUserEmployeeDto dto);
//    AppUserEmployee convertDtoToEntity(AppUserEmployeeDto dto);
}

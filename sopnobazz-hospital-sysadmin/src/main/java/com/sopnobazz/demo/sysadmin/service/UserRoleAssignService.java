package com.sopnobazz.demo.sysadmin.service;

import java.util.List;

import com.sopnobazz.demo.sysadmin.dto.UserRoleAssignDto;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public interface UserRoleAssignService {

    //CRUD function
    UserRoleAssignDto save(UserRoleAssignDto body, int userId);

    UserRoleAssignDto update(UserRoleAssignDto body, int userId);

    UserRoleAssignDto delete(UserRoleAssignDto body);

    List<UserRoleAssignDto> getAll();

    List<UserRoleAssignDto> getAllActive();

    Page<UserRoleAssignDto> getPageableList(int page, int size);

    //business function
}

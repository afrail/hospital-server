package com.sopnobazz.demo.sysadmin.service;

import java.util.List;

import com.sopnobazz.demo.comon.entity.UserRole;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public interface UserRoleService {

    //CRUD function
    UserRole save(UserRole UserRole);

    UserRole update(UserRole UserRole);

    UserRole delete(UserRole UserRole);

    List<UserRole> getAll();

    List<UserRole> getAllActive();

    Page<UserRole> getPageableList(int page, int size);


    //business function
}

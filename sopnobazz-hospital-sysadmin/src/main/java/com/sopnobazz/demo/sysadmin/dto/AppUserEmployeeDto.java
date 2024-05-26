package com.sopnobazz.demo.sysadmin.dto;


import com.sopnobazz.demo.sysadmin.entity.AppUserEmployee;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */


@Data
@EqualsAndHashCode(callSuper = true)
public class AppUserEmployeeDto extends AppUserEmployee {


    private static final long serialVersionUID = 1L;

    boolean generatePassword;
    String password;

}

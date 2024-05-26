package com.sopnobazz.demo.sysadmin.dto;


import java.util.List;

import com.sopnobazz.demo.comon.entity.UserRoleAssign;
import com.sopnobazz.demo.comon.entity.UserRoleAssignMaster;

import lombok.Data;


/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */


@Data
public class UserRoleAssignDto {

    private UserRoleAssignMaster master;
    private List<UserRoleAssign> detailsList;

}

package com.sopnobazz.demo.sysadmin.dto;


import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.ReportRole;
import com.sopnobazz.demo.sysadmin.entity.ReportRolePermission;

import lombok.Data;


/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */


@Data
public class ReportRoleDto {
    private ReportRole reportRole;
    private List<ReportRolePermission> reportPermission;
}

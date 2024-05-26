package com.sopnobazz.demo.sysadmin.service;

import java.util.List;

import com.sopnobazz.demo.sysadmin.dto.ReportRoleAssignDto;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public interface ReportRoleAssignService {

    //CRUD function
    ReportRoleAssignDto save(ReportRoleAssignDto obj, int userId);

    ReportRoleAssignDto update(ReportRoleAssignDto obj, int userId);

    ReportRoleAssignDto delete(ReportRoleAssignDto obj);

    List<ReportRoleAssignDto> getAll();

    List<ReportRoleAssignDto> getAllActive();

    Page<ReportRoleAssignDto> getPageableList(int page, int size);

    //business function
}

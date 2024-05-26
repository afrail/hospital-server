package com.sopnobazz.demo.sysadmin.service;

import java.util.List;

import com.sopnobazz.demo.sysadmin.dto.ReportRoleDto;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public interface ReportRoleService {

    //CRUD function
    ReportRoleDto save(ReportRoleDto obj, int userId);

    ReportRoleDto update(ReportRoleDto obj, int userId);

    ReportRoleDto delete(ReportRoleDto obj);

    List<ReportRoleDto> getAll();

    List<ReportRoleDto> getAllActive();

    Page<ReportRoleDto> getPageableList(int page, int size);

    //business function
}

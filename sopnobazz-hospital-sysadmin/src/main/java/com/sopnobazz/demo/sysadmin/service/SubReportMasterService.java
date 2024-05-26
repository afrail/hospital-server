package com.sopnobazz.demo.sysadmin.service;

import java.io.IOException;
import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.SubReportMaster;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public interface SubReportMasterService {

    //CRUD
    SubReportMaster save(SubReportMaster obj) throws IOException;

    SubReportMaster update(SubReportMaster obj) throws IOException;

    SubReportMaster delete(SubReportMaster obj) throws IOException;

    List<SubReportMaster> getAll();

    SubReportMaster getById(Integer id);

    List<SubReportMaster> getAllActive();

    Page<SubReportMaster> getPageableList(int page, int size);

    //Business
    List<SubReportMaster> getByReportMasterIdAndActive(int reportMasterId, boolean active);
}

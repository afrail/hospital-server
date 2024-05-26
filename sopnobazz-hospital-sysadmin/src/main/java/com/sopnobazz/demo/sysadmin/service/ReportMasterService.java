package com.sopnobazz.demo.sysadmin.service;

import java.io.IOException;
import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.ReportMaster;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public interface ReportMasterService {

    //CRUD
    ReportMaster save(ReportMaster obj) throws IOException;

    ReportMaster update(ReportMaster obj) throws IOException;

    ReportMaster delete(ReportMaster obj) throws IOException;

    List<ReportMaster> getAll();

    ReportMaster getById(Integer id);

    List<ReportMaster> getAllActive();

    Page<ReportMaster> getPageableList(int page, int size);

    List<ReportMaster> getAllAuthorizedReport(int userId);

    //Business

}

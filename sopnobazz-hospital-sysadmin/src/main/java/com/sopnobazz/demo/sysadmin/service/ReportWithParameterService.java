package com.sopnobazz.demo.sysadmin.service;

import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.ReportWithParameter;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public interface ReportWithParameterService {

    //CRUD
    ReportWithParameter save(ReportWithParameter obj);

    ReportWithParameter update(ReportWithParameter obj);

    ReportWithParameter delete(ReportWithParameter obj);

    List<ReportWithParameter> getAll();

    List<ReportWithParameter> getAllActive();

    Page<ReportWithParameter> getPageableList(int page, int size);

    //Business
    List<ReportWithParameter> findByReportMasterId(int reportMasterId);
}

package com.sopnobazz.demo.sysadmin.service;

import java.io.IOException;
import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.ReportUpload;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public interface ReportUploadService {

    //CRUD
    ReportUpload save(MultipartFile file, ReportUpload obj) throws IOException;

    ReportUpload update(MultipartFile file, ReportUpload obj) throws IOException;

    ReportUpload delete(ReportUpload obj) throws IOException;

    List<ReportUpload> getAll();

    List<ReportUpload> getAllActiveSubreport();

    List<ReportUpload> getAllActiveMasterReport();

    ReportUpload getById(Integer id);

    List<ReportUpload> getAllActive();

    Page<ReportUpload> getPageableList(int page, int size);

    //Business

}

package com.sopnobazz.demo.sysadmin.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import com.sopnobazz.demo.comon.utils.CommonUtils;
import com.sopnobazz.demo.sysadmin.entity.ReportUpload;
import com.sopnobazz.demo.sysadmin.report.ReportSources;
import com.sopnobazz.demo.sysadmin.report.ReportUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sopnobazz.demo.sysadmin.repository.ReportUploadRepository;

import lombok.AllArgsConstructor;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Service
@AllArgsConstructor
public class ReportUploadServiceImpl implements ReportUploadService {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(ReportUploadServiceImpl.class);

    private final ReportUploadRepository repo;
    private final CommonUtils commonUtils;
    private final ReportUploader reportUploader;
    private final ReportSources reportSources;

    @PostConstruct
    public void init() {
    }


    @Transactional
    @Override
    public ReportUpload save(MultipartFile file, ReportUpload entity) throws IOException {

        //save report to server
        Map<String, String> reportInfo = reportUploader.uploadMasterReportToServer(file, entity.getFileName());

        //is subreport
        if (ObjectUtils.isEmpty(entity.getIsSubreport())) {
            entity.setIsSubreport(false);
        }
        //ini
        entity.setFileName(reportInfo.get("fileNameEng"));
        entity.setFileLocation(reportInfo.get("fileLocation"));
        entity.setFileNameJasper(reportInfo.get("fileNameJasperEng"));
        commonUtils.setEntryUserInfo(entity);

        //save report info to database
        return repo.save(entity);
    }

    @Transactional
    @Override
    public ReportUpload update(MultipartFile file, ReportUpload entity) throws IOException {

        ReportUpload data = repo.findById(entity.getId()).get();
        if (ObjectUtils.isEmpty(data)) {
            return null;
        }

        Path path = Paths.get(reportSources.getSourceReport(data.getFileName()));
        if (Files.exists(path)) {
            Files.delete(path);
        }

        //save report to server
        Map<String, String> reportInfo = reportUploader.uploadMasterReportToServer(file, entity.getFileName());

        //is subreport
        if (ObjectUtils.isEmpty(entity.getIsSubreport())) {
            entity.setIsSubreport(false);
        }

        //ini
        entity.setFileName(reportInfo.get("fileNameEng"));
        entity.setFileLocation(reportInfo.get("fileLocation"));
        entity.setFileNameJasper(reportInfo.get("fileNameJasperEng"));
        commonUtils.setUpdateUserInfo(entity, data);

        //save report info to database
        return repo.save(entity);
    }

    @Transactional
    @Override
    public ReportUpload delete(ReportUpload entity) throws IOException {

        ReportUpload data = repo.findById(entity.getId()).get();
        if (ObjectUtils.isEmpty(data)) {
            return null;
        }

        Path path = Paths.get(reportSources.getSourceReport(data.getFileName()));
        if (Files.exists(path)) {
            Files.delete(path);
        }
        repo.delete(entity);
        return entity;
    }

    @Override
    public List<ReportUpload> getAll() {
        return repo.findAll();
    }


    @Override
    public List<ReportUpload> getAllActiveSubreport() {
        return repo.findAllActiveSubreport();
    }

    @Override
    public List<ReportUpload> getAllActiveMasterReport() {
        return repo.findAllActiveMasterReport();
    }

    @Override
    public List<ReportUpload> getAllActive() {
        return repo.findByActive(true);
    }

    @Override
    public Page<ReportUpload> getPageableList(int page, int size) {
        PageRequest pageRequest = commonUtils.getPageRequest(page, size);
        Page<ReportUpload> pageresult = repo.findAll(pageRequest);
        List<ReportUpload> objlist = pageresult.stream()
                .collect(Collectors.toList());
        return new PageImpl<>(objlist, pageRequest, pageresult.getTotalElements());
    }


    @Override
    public ReportUpload getById(Integer id) {
        return repo.findById(id).get();
    }

}

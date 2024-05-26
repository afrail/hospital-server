package com.sopnobazz.demo.sysadmin.report;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.ReportMaster;
import com.sopnobazz.demo.sysadmin.entity.SubReportMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ResourceUtils;

import com.sopnobazz.demo.sysadmin.service.ReportMasterService;
import com.sopnobazz.demo.sysadmin.service.SubReportMasterService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRSaver;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Component
public class ReportCompilation {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(ReportCompilation.class);

    @Autowired
    ReportSources reportSources;

    @Autowired
    ReportMasterService reportMasterservice;

    @Autowired
    SubReportMasterService subReportMasterService;


    public JasperReport compileReports(Integer id) throws JRException, IOException {

        ReportMaster masterReport = reportMasterservice.getById(id);
        if (ObjectUtils.isEmpty(masterReport)) {
            return null;
        }

        String compileDir = reportSources.REPORT_COMPILE_DIR;
        Path compilePath = Paths.get(compileDir);
        if (!Files.exists(compilePath)) {
            Files.createDirectories(Paths.get(compileDir));
        }

        //compile master report
        File reportFile = ResourceUtils.getFile(reportSources.getSourceReport(masterReport.getReportUpload().getFileName()));
        JasperReport report = JasperCompileManager.compileReport(reportFile.getAbsolutePath());
        JRSaver.saveObject(report, reportSources.getCompiledReport(masterReport.getReportUpload().getFileNameJasper()));

        //compile subreport
        List<SubReportMaster> subreportMasterList = subReportMasterService.getByReportMasterIdAndActive(masterReport.getId(),
                true);
        if (!CollectionUtils.isEmpty(subreportMasterList)) {
            for (SubReportMaster subreport : subreportMasterList) {
                File subReportFile = ResourceUtils.getFile(reportSources.getSourceReport(subreport.getReportUpload().getFileName()));
                JasperReport subReport = JasperCompileManager.compileReport(subReportFile.getAbsolutePath());
                JRSaver.saveObject(subReport, reportSources.getCompiledReport(subreport.getReportUpload().getFileNameJasper()));
            }
        }

        return report;
    }

}

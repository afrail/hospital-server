package com.sopnobazz.demo.sysadmin.repository;

import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.ReportUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Repository
public interface ReportUploadRepository extends JpaRepository<ReportUpload, Integer> {
    List<ReportUpload> findByActive(boolean active);

    String findSubreportQuery = "SELECT report.*\r\n"
            + "FROM SYS_REPORT_UPLOAD report\r\n"
            + "WHERE report.IS_SUBREPORT = true \r\n"
            + "ORDER BY report.CODE ASC";

    @Query(value = findSubreportQuery, nativeQuery = true)
    List<ReportUpload> findAllActiveSubreport();


    String findMasterReportQuery = "SELECT report.*\r\n"
            + "FROM SYS_REPORT_UPLOAD report\r\n"
            + "WHERE report.IS_SUBREPORT = false \r\n"
            + "ORDER BY report.CODE ASC";

    @Query(value = findMasterReportQuery, nativeQuery = true)
    List<ReportUpload> findAllActiveMasterReport();
}

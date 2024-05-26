package com.sopnobazz.demo.sysadmin.repository;

import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.ReportWithParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Repository
public interface ReportWithParameterRepository extends JpaRepository<ReportWithParameter, Integer> {

    List<ReportWithParameter> findByActive(boolean active);

    @Query(value = "SELECT max(id) FROM ReportWithParameter")
    int getMaxId();

    String paramInfoQuery = "SELECT report.*\r\n"
            + "FROM sys_report_with_parameter report\r\n"
            + "WHERE report.report_master_id = :reportId AND report.active = true \r\n"
            + "ORDER BY report.serial ASC";

    @Query(value = paramInfoQuery, nativeQuery = true)
    List<ReportWithParameter> findParamInfoByReportMasterId(
            @Param("reportId") Integer reportId

    );
}

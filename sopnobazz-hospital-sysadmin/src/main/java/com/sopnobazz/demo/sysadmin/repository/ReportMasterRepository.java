package com.sopnobazz.demo.sysadmin.repository;

import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.ReportMaster;
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
public interface ReportMasterRepository extends JpaRepository<ReportMaster, Integer> {
    List<ReportMaster> findByActive(boolean active);

    String findActiveReportBySerial = "SELECT report.*\r\n"
            + "FROM SYS_REPORT_MASTER report\r\n"
            + "WHERE report.ACTIVE = true \r\n"
            + "ORDER BY report.SERIAL ASC";

    @Query(value = findActiveReportBySerial, nativeQuery = true)
    List<ReportMaster> findByActiveAndSerial();
}

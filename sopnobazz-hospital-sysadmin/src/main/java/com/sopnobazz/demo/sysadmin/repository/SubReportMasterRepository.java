package com.sopnobazz.demo.sysadmin.repository;

import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.SubReportMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

@Repository
public interface SubReportMasterRepository extends JpaRepository<SubReportMaster, Integer> {
    List<SubReportMaster> findByActive(boolean active);

    List<SubReportMaster> findByReportMasterIdAndActive(int reportMasterId, boolean active);
}

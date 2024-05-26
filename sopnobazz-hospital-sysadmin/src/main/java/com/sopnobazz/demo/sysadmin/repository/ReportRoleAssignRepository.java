package com.sopnobazz.demo.sysadmin.repository;


import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.ReportRoleAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */


@Repository
public interface ReportRoleAssignRepository extends JpaRepository<ReportRoleAssign, Integer> {
    List<ReportRoleAssign> findAllByOrderByIdDesc();

    List<ReportRoleAssign> findByActive(boolean active);

    List<ReportRoleAssign> findByAppUserId(Integer id);

    ReportRoleAssign findByAppUserIdAndReportRoleId(Integer appUserId, Integer reportRoleId);
}

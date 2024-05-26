package com.sopnobazz.demo.sysadmin.repository;


import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.ReportRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */


@Repository
public interface ReportRolePermissionRepository extends JpaRepository<ReportRolePermission, Integer> {
    List<ReportRolePermission> findByActive(boolean active);

    List<ReportRolePermission> findByReportRoleId(int roleId);

    List<ReportRolePermission> findByReportRoleIdAndActive(int roleId, boolean active);
}

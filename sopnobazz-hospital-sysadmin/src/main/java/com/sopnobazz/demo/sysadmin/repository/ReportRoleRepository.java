package com.sopnobazz.demo.sysadmin.repository;


import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.ReportRole;
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
public interface ReportRoleRepository extends JpaRepository<ReportRole, Integer> {
    List<ReportRole> findByActive(boolean active);

    @Query("SELECT coalesce(max(id), 0) + 1 FROM ReportRole")
    Integer getMaxId();
}

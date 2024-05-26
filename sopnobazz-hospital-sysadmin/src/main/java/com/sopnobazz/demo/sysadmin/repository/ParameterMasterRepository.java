package com.sopnobazz.demo.sysadmin.repository;

import java.util.List;

import com.sopnobazz.demo.sysadmin.entity.ParameterMaster;
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
public interface ParameterMasterRepository extends JpaRepository<ParameterMaster, Integer> {

    List<ParameterMaster> findByActive(boolean active);

    @Query(value = "SELECT max(id) FROM ParameterMaster")
    int getMaxId();
}

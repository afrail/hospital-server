package com.sopnobazz.demo.comon.repository;


import com.sopnobazz.demo.comon.entity.AppUserLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @version 2.0.0
 * @Since MARCH 30, 2022
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */

@Repository
public interface AppUserLogRepository extends JpaRepository<AppUserLog, Integer> {
}

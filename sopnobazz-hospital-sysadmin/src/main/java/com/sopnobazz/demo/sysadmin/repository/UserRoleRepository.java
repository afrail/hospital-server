package com.sopnobazz.demo.sysadmin.repository;


import java.util.List;

import com.sopnobazz.demo.comon.entity.UserRole;
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
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findByActive(boolean active);

    @Query("SELECT coalesce(max(id), 0) + 1 FROM UserRole")
    Integer getMaxId();
}

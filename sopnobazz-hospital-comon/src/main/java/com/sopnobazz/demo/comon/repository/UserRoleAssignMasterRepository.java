package com.sopnobazz.demo.comon.repository;

import com.sopnobazz.demo.comon.entity.UserRoleAssignMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @version 2.0.0
 * @Since 4/26/2022
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */

public interface UserRoleAssignMasterRepository extends JpaRepository<UserRoleAssignMaster, Integer> {
    List<UserRoleAssignMaster> findByActive(boolean active);
}

package com.sopnobazz.demo.comon.repository;


import java.util.List;

import com.sopnobazz.demo.comon.entity.UserRoleAssign;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0.0
 * @Since July 10, 2021
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */

@Repository
public interface UserRoleAssignRepository extends JpaRepository<UserRoleAssign, Integer> {
    List<UserRoleAssign> findByMasterIdOrderById(Integer masterId);

    List<UserRoleAssign> findByActive(boolean active);

    List<UserRoleAssign> findByAppUserId(Integer id);

    //	List<UserRoleAssign>  findByAppUserIdOrderByUserRoleRolePermissionListMenuItemUrlMenuItemSerialNo(Integer id);
    UserRoleAssign findByAppUserIdAndUserRoleId(Integer appUserId, Integer userRoleId);

    String distinctQuery = "select * from \n"
            + "(select DISTINCT ON (app_user_id) app_user_id, id,active,entry_date,entry_user,update_date,update_user,\n"
            + "user_role_id,emp_code,app_user_code,entry_app_user_code,update_app_user_code,is_bof_before \n"
            + "from SYA_USER_ROLE_ASSIGN) SYA_USER_ROLE_ASSIGN\n"
            + "order by id desc";

    @Query(value = distinctQuery, nativeQuery = true)
    List<UserRoleAssign> findDistinctByAppUser(
            Pageable pageable
    );

}

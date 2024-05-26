package com.sopnobazz.demo.sysadmin.repository;


import java.util.List;
import java.util.Optional;

import com.sopnobazz.demo.sysadmin.entity.AppUserEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */


@Repository
public interface AppUserEmployeeRepository extends JpaRepository<AppUserEmployee, Integer> {
    Optional<AppUserEmployee> findByActiveAndAppUserUsernameIgnoreCase(boolean active, String username);

    List<AppUserEmployee> findByActive(boolean active);

    AppUserEmployee findByAppUserIdAndActive(Integer id, boolean active);

    String appUserEmployeeSearchQuery = "select appuseremp.* from hrm_employee_personal_information personal \r\n"
            + "join sya_app_user_employee appuseremp on appuseremp .employee_id = personal .id \r\n"
            + "where 1 = 1  \r\n"
            + "and appuseremp.employee_id = :empId  \r\n"
            + "order by employee_name";

    @Query(value = appUserEmployeeSearchQuery, nativeQuery = true)
    List<AppUserEmployee> getByEmployeeId(
            @Param("empId") Integer empId
    );


    String findByTransactionIdAndTransactionTableQuery = "select * from sya_app_user_employee appemp\n"
            + "where  appemp.app_user_id in (\n"
            + "	select  from_user_id  \n"
            + "	from sya_approval_history sah \n"
            + "	where transaction_id = :transactionId\n"
            + "	and transaction_table = :transactionTable\n"
            + "	and from_user_id is not null\n"
            + "	union \n"
            + "	select  default_user_id  \n"
            + "	from sya_approval_history sah \n"
            + "	where transaction_id = :transactionId\n"
            + "	and transaction_table = :transactionTable\n"
            + "	and default_user_id is not null\n"
            + ")";

    @Query(value = findByTransactionIdAndTransactionTableQuery, nativeQuery = true)
    List<AppUserEmployee> findByTransactionIdAndTransactionTable(
            @Param("transactionId") Integer transactionId,
            @Param("transactionTable") String transactionTable
    );


}

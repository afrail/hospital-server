package com.sopnobazz.demo.comon.repository;

import java.util.List;

import com.sopnobazz.demo.comon.entity.CommonMessageHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @version 2.0.0
 * @Since 3/23/2022
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */

@Repository
public interface CommonMessageHistoryRepository extends JpaRepository<CommonMessageHistory, Integer> {

    String empNameSearchQuery = "select personal.* from COMMON_MESSAGE_HISTORY personal \r\n"
            + "where 1 = 1 \r\n"
            + "and personal.APP_USER_NAME = :userName \r\n"
            + "and personal.close = false \r\n"
            + "order by personal.id desc \r\n";

    @Query(value = empNameSearchQuery, nativeQuery = true)
    List<CommonMessageHistory> getAllByNotRemoveList(
            @Param("userName") String userName
    );
}

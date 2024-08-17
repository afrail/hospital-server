package com.sopnobazz.demo.comon.repository;


import java.util.List;

import com.sopnobazz.demo.comon.entity.ApprovalTeamDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * @version 1.0.0
 * @Since July 25, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */


@Repository
public interface ApprovalTeamDetailsRepository extends JpaRepository<ApprovalTeamDetails, Integer> {
    List<ApprovalTeamDetails> findByActive(boolean active);

//	String nQuery = "SELECT DTLS.ID, DTLS.APP_USER_ID \"appuserid\", master.id \"teamid\", master.MODULE_ID \"moduleid\"  \r\n"
//			+ "FROM SYA_APPROVAL_TEAM_DETAILS DTLS, SYA_APPROVAL_TEAM_MASTER_APPROVAL_TEAM_DETAIL_LIST LIST, sya_approval_team_master master\r\n"
//			+ "WHERE DTLS.ID = LIST.APPROVAL_TEAM_DETAIL_LIST_ID\r\n"
//			+ "and master.id = LIST.APPROVAL_TEAM_MASTER_ID\r\n"
//			+ "AND DTLS.APP_USER_ID = :id\r\n"
//			+ "AND master.active = true";

    String nQuery = "select config.id, dtls.app_user_id appuserid, master.id teamid, config.module_id moduleid, config.office_id officeid\r\n"
            + "from sya_approval_configuration config, \r\n"
            + "sya_approval_team_master master, sya_approval_team_details dtls,  sya_approval_team_master_approval_team_detail_list list\r\n"
            + "where 1=1\r\n"
            + "and (config.from_approval_team = master.id or config.to_approval_team = master.id)\r\n"
            + "and dtls.id = list.approval_team_detail_list_id\r\n"
            + "and master.id = list.approval_team_master_id\r\n"
            + "and dtls.app_user_id = :id\r\n"
            + "and config.active = true";

    @Query(value = nQuery, nativeQuery = true)
    List<ApprovalUser> findByAppUserId(
            @Param("id") Integer id
    );

    interface ApprovalUser {

        String getId();

        String getAppuserid();

        String getTeamid();

        String getModuleid();

        String getOfficeid();

    }

}

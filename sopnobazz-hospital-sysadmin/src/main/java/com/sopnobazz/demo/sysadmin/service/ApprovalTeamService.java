package com.sopnobazz.demo.sysadmin.service;

import java.util.List;

import com.sopnobazz.demo.comon.entity.ApprovalTeamMaster;
import org.springframework.data.domain.Page;

/**
 * @version 1.0.0
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 */

public interface ApprovalTeamService {

    //CRUD function
    ApprovalTeamMaster save(ApprovalTeamMaster ApprovalTeamMaster);

    ApprovalTeamMaster update(ApprovalTeamMaster ApprovalTeamMaster);

    ApprovalTeamMaster delete(ApprovalTeamMaster ApprovalTeamMaster);

    List<ApprovalTeamMaster> getAll();

    List<ApprovalTeamMaster> getAllActive();

    Page<ApprovalTeamMaster> getPageableList(int page, int size);

    //business function
    List<ApprovalTeamMaster> getByModuleId(Integer moduleId);

}

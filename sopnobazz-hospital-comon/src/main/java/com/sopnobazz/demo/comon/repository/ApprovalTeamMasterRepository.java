package com.sopnobazz.demo.comon.repository;


import java.util.List;

import com.sopnobazz.demo.comon.entity.ApprovalTeamMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @version 1.0.0
 * @Since July 25, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */

@Repository
public interface ApprovalTeamMasterRepository extends JpaRepository<ApprovalTeamMaster, Integer> {
    List<ApprovalTeamMaster> findByActive(boolean active);

    List<ApprovalTeamMaster> findBymoduleIdAndActive(Integer moduleId, boolean active);
}

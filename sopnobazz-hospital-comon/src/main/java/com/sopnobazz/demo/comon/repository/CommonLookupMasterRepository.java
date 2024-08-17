package com.sopnobazz.demo.comon.repository;


import java.util.List;

import com.sopnobazz.demo.comon.entity.CommonSetupMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0.0
 * @Since May 20, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 */
@Repository
public interface CommonLookupMasterRepository extends JpaRepository<CommonSetupMaster, Integer> {

    @Query("SELECT coalesce(max(id), 0) + 1 FROM CommonSetupMaster")
    Integer getMaxId();

    String queryForMovement = "select clm.* from common_lookup_master clm \n" +
            "where 1 = 1\n" +
            "and clm.id in (20, 21, 22, 85, 86, 19)\n" +
            "order by clm.name ";

    @Query(value = queryForMovement, nativeQuery = true)
    List<CommonSetupMaster> getMovementConfigMasterValue();

    List<CommonSetupMaster> findByActive(boolean active);

    List<CommonSetupMaster> findByParentId(int id);
}

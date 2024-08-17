package com.sopnobazz.demo.doctor_patient.repository;


import com.sopnobazz.demo.doctor_patient.entity.EhmCommonLookupMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Since Dec 07, 2021
 * @Author Mohaiminul Haq - 582
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Repository
public interface EhmCommonLookupMasterRepository extends JpaRepository<EhmCommonLookupMaster, Integer> {
    List<EhmCommonLookupMaster> findByActive(boolean active);
}

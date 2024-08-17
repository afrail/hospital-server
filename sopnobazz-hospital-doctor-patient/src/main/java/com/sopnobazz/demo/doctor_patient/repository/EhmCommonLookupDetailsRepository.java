package com.sopnobazz.demo.doctor_patient.repository;


import com.sopnobazz.demo.doctor_patient.entity.EhmCommonLookupDetails;
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
public interface EhmCommonLookupDetailsRepository extends JpaRepository<EhmCommonLookupDetails, Integer> {
    List<EhmCommonLookupDetails> findByActive(boolean active);
    List<EhmCommonLookupDetails> findByMasterIdAndActiveOrderByIdDesc(int id, boolean active);

    List<EhmCommonLookupDetails> findByMasterIdAndActiveOrderByIdAsc(int id, boolean active);

}

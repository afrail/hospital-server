package com.sopnobazz.demo.doctor_patient.repository;
import com.sopnobazz.demo.doctor_patient.entity.MedicineMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Since Dec 07, 2021
 * @Author Mohaiminul Haq - 582
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Repository
public interface MedicineMasterRepository extends JpaRepository<MedicineMaster, Integer> {
    List<MedicineMaster> findByActive(boolean active);
    String searchQuery = "select med.*\n"
    		+ "from ehm_medicine_master med\n"
    		+ "where 1=1\n"
    		+ "and med.MEDICINE_NAME ilike %:searchValue%\n"
    		+ "order by med.MEDICINE_NAME";

    @Query(value = searchQuery, nativeQuery = true)
    List<MedicineMaster> findBySearchValue(
            @Param("searchValue") String searchValue
    );

}

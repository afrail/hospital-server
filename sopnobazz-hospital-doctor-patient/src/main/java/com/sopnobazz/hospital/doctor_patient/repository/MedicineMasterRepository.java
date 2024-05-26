package com.sopnobazz.hospital.doctor_patient.repository;
import com.sopnobazz.hospital.doctor_patient.entity.MedicineMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    Page<MedicineMaster> findByHosType(Integer hosType, Pageable pageable);
    
    List<MedicineMaster> findByHosType(Integer hosType);
    
    String searchQuery = "select med.*\n"
    		+ "from ehm_medicine_master med\n"
    		+ "where 1=1\n"
    		+ "and med.MEDICINE_NAME ilike %:searchValue%\n"
    		+ "order by med.MEDICINE_NAME";

    @Query(value = searchQuery, nativeQuery = true)
    List<MedicineMaster> findBySearchValue(
            @Param("searchValue") String searchValue
    );
    
    
    String dentalAvailableQuery = "select * \n"
    		+ "from ehm_medicine_master \n"
    		+ "where 1=1\n"
    		+ "and CASE WHEN :hosType = 2 THEN AVAILABLE_STOCK_DENTAL > 0 ELSE available_stock_pathology > 0 END\n"
    		+ "order by id desc";

    @Query(value = dentalAvailableQuery, nativeQuery = true)
    List<MedicineMaster> findAvaileItemByHosType(
    		@Param("hosType") Integer hosType
    		);

    String pageAvailQuery = "SELECT *, count(*) OVER () AS total_rows\n" +
            "FROM   ehm_medicine_master\n" +
            "LIMIT  100\n" +
            "OFFSET 0;\n";

    @Query(value = dentalAvailableQuery, nativeQuery = true)
    List<MedicineMaster> pageAvaileItemByHosType(
            @Param("hosType") Integer hosType
    );
}

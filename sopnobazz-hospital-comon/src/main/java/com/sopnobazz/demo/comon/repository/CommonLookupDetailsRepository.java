package com.sopnobazz.demo.comon.repository;

/**
 * @Since May 20, 2021
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @version 1.0.0
 */

import java.util.List;

import com.sopnobazz.demo.comon.entity.CommonSetupDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonLookupDetailsRepository extends JpaRepository<CommonSetupDetails, Integer> {
    List<CommonSetupDetails> findByActive(boolean active);

    List<CommonSetupDetails> findByMasterIdAndActiveOrderByName(int id, boolean active);

    @Query(value = "SELECT max(id) FROM CommonSetupDetails")
    int getMaxId();

    String detailsQuery = "WITH RECURSIVE nodes(id,lookup_name,bangla_name,parent_id,master_id) AS (\r\n"
            + "    SELECT s1.id, s1.name,s1.bangla_name ,s1.parent_id ,s1.master_id\r\n"
            + "    FROM common_lookup_details s1 WHERE 1=1 and id=106\r\n"
            + "        UNION\r\n"
            + "    SELECT s2.id, s2.name,s2.bangla_name, s2.parent_id, s2.master_id\r\n"
            + "    FROM common_lookup_details s2, nodes s1 WHERE s2.parent_id = s1.id\r\n"
            + ")\r\n"
            + "SELECT a.id,a.lookup_name as name, a.bangla_name,a.parent_id\r\n"
            + ",null active, null code, null entry_date, null entry_user, null update_date, null update_user \r\n"
            + ",null master_id, null emp_code, null app_user_code, null entry_app_user_code, null update_app_user_code, null short_code \r\n"
            + "FROM nodes a , common_lookup_master b \r\n"
            + "where a.master_id=b.id";

    @Query(value = detailsQuery, nativeQuery = true)
    List<CommonSetupDetails> getOfficeInfo();

    String searchQuery = "select cld.* from common_lookup_details cld\r\n"
            + "where 1 = 1\r\n"
            + "and cld.master_id = :masterId\r\n"
            + "and LOWER(cld.name) ilike %:searchValue%\r\n"
            + "order by cld.id asc";

    @Query(value = searchQuery, nativeQuery = true)
    List<CommonSetupDetails> getByMasterIdAndSearchValue(
            @Param("masterId") Integer masterId,
            @Param("searchValue") String searchValue
    );

    String dependentSearchQuery = "select cld.* from common_lookup_details cld\r\n"
            + "where 1 = 1\r\n"
            + "and cld.parent_id = :parentId\r\n"
            + "order by cld.id asc";

    @Query(value = dependentSearchQuery, nativeQuery = true)
    List<CommonSetupDetails> getByDependentMasterIdAndSearchValue(
            @Param("parentId") Integer parentId
    );
}

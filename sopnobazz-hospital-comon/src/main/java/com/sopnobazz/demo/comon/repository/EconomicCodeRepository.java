package com.sopnobazz.demo.comon.repository;

import com.sopnobazz.demo.comon.entity.EconomicCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 2.0.0
 * @Since November 17, 2021
 * @Author Rokon Uddin 560
 */

@Repository
public interface EconomicCodeRepository extends JpaRepository<EconomicCode, Integer> {

    List<EconomicCode> findByActiveAndParentCode(boolean active, boolean parentIs);

    String codeTypeQuery = "select code.* from BUDGET_ECONOMIC_CODE code \r\n"
            + "where 1 = 1 \r\n"
            + "and CODE_TYPE= :codeType \r\n"
            + "order by id \r\n";

    @Query(value = codeTypeQuery, nativeQuery = true)
    List<EconomicCode> findByCodeType(boolean codeType);

    String codeTypePagableQuery = "select code.* from BUDGET_ECONOMIC_CODE code \r\n"
            + "where 1 = 1 \r\n"
            + "and CODE_TYPE= :codeType \r\n";

    @Query(value = codeTypePagableQuery, nativeQuery = true)
    Page<EconomicCode> findByCodeType(boolean codeType, Pageable pageable);


    EconomicCode findByEconomicCode(String economicCode);


    String empNameSearchQuery = "select code.* from BUDGET_ECONOMIC_CODE code \r\n"
            + "where 1 = 1 \r\n"
            + "and code.is_parent = :parentValue \r\n"
            + "and active= true \r\n"
            + "and CODE_TYPE = true \r\n"
            + "order by id \r\n";

    @Query(value = empNameSearchQuery, nativeQuery = true)
    List<EconomicCode> findByParent(
            @Param("parentValue") boolean parentValue
    );

    String economicCodeQuery = "select code.* from BUDGET_ECONOMIC_CODE code \r\n"
            + "where 1 = 1 \r\n"
            + "and is_parent is false \r\n"
            + "and code_type is true \r\n"
            + "order by id \r\n";

    @Query(value = economicCodeQuery, nativeQuery = true)
    List<EconomicCode> getEconomicCode();
}

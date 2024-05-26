package com.sopnobazz.demo.comon.repository;

import java.util.List;

import com.sopnobazz.demo.comon.entity.FinancialYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 2.0.0
 * @Since November 17, 2021
 * @Author Rokon Uddin 560
 */

@Repository
public interface FinancialYearRepository extends JpaRepository<FinancialYear, Integer> {
    List<FinancialYear> findByActiveOrderByStartDateAsc(boolean active);

    List<FinancialYear> findByCloseIs(boolean close);
}

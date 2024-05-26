package com.sopnobazz.demo.comon.repository;


import java.util.List;

import com.sopnobazz.demo.comon.entity.SmsFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @version 1.0.0
 * @Since August 23, 2021
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */


@Repository
public interface SmsFactoryRepository extends JpaRepository<SmsFactory, Integer> {
    List<SmsFactory> findByStatus(String status);
}

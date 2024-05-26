package com.sopnobazz.demo.comon.repository;


import java.util.List;

import com.sopnobazz.demo.comon.entity.PasswordPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @version 1.0.0
 * @Since July 07, 2021
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 */

@Repository
public interface PasswordPolicyRepository extends JpaRepository<PasswordPolicy, Integer> {
    List<PasswordPolicy> findByActive(boolean active);

}

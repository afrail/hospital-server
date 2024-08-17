/**
 *
 */
package com.sopnobazz.demo.comon.repository;

import java.util.Optional;

import com.sopnobazz.demo.comon.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project demo-management
 * @Author Md. Nayeemul Islam
 * @Since Aug 9, 2022
 * @version 1.0.0
 */
public interface UserBalanceRepository extends JpaRepository<UserBalance, Integer> {

    Optional<UserBalance> findByAppUserId(Integer id);
}

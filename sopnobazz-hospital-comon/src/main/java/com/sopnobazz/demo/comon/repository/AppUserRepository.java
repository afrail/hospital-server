/**
 *
 */
package com.sopnobazz.demo.comon.repository;

import java.util.List;
import java.util.Optional;

import com.sopnobazz.demo.comon.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Project ibcs-bof-erp
 * @Author Md. Nayeemul Islam
 * @Project demo-management
 * @version 1.0.0
 */

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsernameIgnoreCase(String username);

    List<AppUser> findByActive(boolean active);

    List<AppUser> findByUsernameAndActive(String code, boolean active);
}

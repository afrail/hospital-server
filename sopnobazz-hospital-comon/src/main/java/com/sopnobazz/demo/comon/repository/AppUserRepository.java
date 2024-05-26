/**
 *
 */
package com.sopnobazz.demo.comon.repository;

import java.util.List;
import java.util.Optional;

import com.sopnobazz.demo.comon.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project ibcs-bof-erp
 * @Author Md. Nayeemul Islam
 * @Project hospital-management
 * @version 1.0.0
 */
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByUsernameIgnoreCase(String username);

    List<AppUser> findByActive(boolean active);

    List<AppUser> findByUsernameAndActive(String code, boolean active);
}

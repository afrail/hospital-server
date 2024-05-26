/**
 *
 */
package com.sopnobazz.demo.sysadmin.doctor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Project Demo
 * @Author Afrail Hossain
 * @Since Nov 16, 2022
 * @version 1.0.0
 */

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    List<Doctor> findByActive(boolean active);
}

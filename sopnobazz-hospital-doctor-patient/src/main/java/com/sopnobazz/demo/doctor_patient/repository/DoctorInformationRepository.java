package com.sopnobazz.demo.doctor_patient.repository;


import com.sopnobazz.demo.doctor_patient.entity.DoctorInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorInformationRepository extends JpaRepository<DoctorInformation, Integer> {
    List<DoctorInformation> findByActive(boolean active);

    String getByAppUser = "SELECT master.*\n"
            + "FROM EHM_DOCTOR_AND_STAFF_INFORMATION master\n"
            + "WHERE 1 = 1\n"
            + "and master.app_user_id  = :appuser";

    @Query(value = getByAppUser, nativeQuery = true)
    DoctorInformation findByAppUserId(
            @Param("appuser") Integer appuser
    );

}

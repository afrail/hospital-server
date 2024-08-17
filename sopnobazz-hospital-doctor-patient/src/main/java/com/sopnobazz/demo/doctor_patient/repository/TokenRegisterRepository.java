package com.sopnobazz.demo.doctor_patient.repository;

import com.sopnobazz.demo.doctor_patient.entity.TokenRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface TokenRegisterRepository extends JpaRepository<TokenRegister, Integer> {
    List<TokenRegister> findByActive(boolean active);

    String countQuery = "select count(id) from ehm_token_register etr\n"
    		+ "where date(visit_date) = :visitDate\n"
    		+ "AND COALESCE(refer_to_doctor_id, 1) = CASE WHEN :docId = 0 THEN COALESCE(refer_to_doctor_id, 1) ELSE :docId END";
    @Query(value = countQuery, nativeQuery = true)
    int countTodayRegister(
    		@Param("visitDate") Date visitDate,
    		@Param("docId") Integer docId
    		);

	String getQuery = "SELECT master.*\n"
			+ "FROM EHM_TOKEN_REGISTER master\n"
			+ "WHERE 1 = 1\n"
			+ "AND COALESCE(master.refer_to_doctor_id , 1) = CASE WHEN :doctorId = 0 THEN COALESCE(master.refer_to_doctor_id, 1) ELSE :doctorId END\n"
			+ "and ((cast(:fromDate as date) IS NULL OR cast(:toDate as date) IS NULL) OR date(master.VISIT_DATE) BETWEEN :fromDate AND :toDate)\n"
			+ "order by id desc";
	@Query(value = getQuery, nativeQuery = true)
	List<TokenRegister> getBySearchParam(
			@Param("doctorId") Integer doctorId,
			@Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate
	);

	String doctorTokenQuery = "select regi.*\n" +
			"from ehm_token_register regi, ehm_doctor_and_staff_information doctor\n" +
			"where 1=1\n" +
			"and doctor.app_user_id = :appUserId\n" +
			"and doctor.id = regi.refer_to_doctor_id \n" +
			"and date(regi.visit_date) = current_date\n" +
			"and regi.active = true\n" +
			"and regi.ACTION_TOKEN is not true\n" +
			"order by regi.is_absence, regi.id";
	@Query(value = doctorTokenQuery, nativeQuery = true)
	List<TokenRegister> getTokenByDoctor(
			@Param("appUserId") Integer appUserId
	);



}


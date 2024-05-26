package com.sopnobazz.hospital.doctor_patient.repository;

import com.sopnobazz.hospital.doctor_patient.entity.TokenRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Since Dec 21, 2021
 * @Author Debobrato Biswas - V00005
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */
@Repository
public interface TokenRegisterRepository extends JpaRepository<TokenRegister, Integer> {
    List<TokenRegister> findByActive(boolean active);
    Page<TokenRegister> findAllByHosTypeOrderByIdDesc(int hosType, Pageable pageable);
    
    String getQuery = "SELECT master.*\n"
    		+ "FROM EHM_TOKEN_REGISTER master\n"
    		+ "left outer join ehm_patient_info patInfo on patinfo.id = master.patient_id\n"
    		+ "WHERE 1 = 1\n"
    		+ "AND COALESCE(master.refer_to_doctor_id , 1) = CASE WHEN :doctorId = 0 THEN COALESCE(master.refer_to_doctor_id, 1) ELSE :doctorId END\n"
			+ "AND COALESCE(master.VISIT_TYPE , 1) = CASE WHEN :visitType = 0 THEN COALESCE(master.VISIT_TYPE, 1) ELSE :visitType END\n"
			+ "AND COALESCE(master.HOSPITAL_TYPE , 0) = CASE WHEN :hospitalType = 0 THEN COALESCE(master.HOSPITAL_TYPE, 0) ELSE :hospitalType END\n"
    		+ "and ((cast(:fromDate as date) IS NULL OR cast(:toDate as date) IS NULL) OR date(master.VISIT_DATE) BETWEEN :fromDate AND :toDate)\n"
    		+ "AND patinfo.patient_code ILIKE %:patientCode%\n"
    		+ "and master.HOS_TYPE = :hosType \n"
    		+ "order by id desc";
	@Query(value = getQuery, nativeQuery = true)
    List<TokenRegister> getBySearchParam(
    		@Param("doctorId") Integer doctorId,
            @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate,
            @Param("patientCode") String patientCode,
			@Param("hosType") Integer hosType,
			@Param("visitType") Integer visitType,
			@Param("hospitalType") Integer hospitalType
    );
    
    
    String searchQuery = "select * from EHM_TOKEN_REGISTER\r\n"
            + "where 1 = 1 \r\n"
            + "and TOKEN_NUMBER ilike %:searchValue% \r\n"
//            + "and ACTION_TOKEN = false \r\n"
//            + "and TOKEN_TYPE = 2 \r\n"
            + "order by TOKEN_NUMBER \r\n";

    @Query(value = searchQuery, nativeQuery = true)
    List<TokenRegister> findBySearchValue(
    		@Param("searchValue") String searchValue
    );
    
    String countQuery = "select count(id) from ehm_token_register etr\n"
    		+ "where date(visit_date) = :visitDate\n"
    		+ "and TOKEN_TYPE = :tokenType\n"
    		+ "and hospital_type = :hospitalType\n"
    		+ "AND COALESCE(refer_to_doctor_id, 1) = CASE WHEN :docId = 0 THEN COALESCE(refer_to_doctor_id, 1) ELSE :docId END";

    @Query(value = countQuery, nativeQuery = true)
    int countTodayRegister(
    		@Param("visitDate") Date visitDate,
    		@Param("tokenType") Integer tokenType,
    		@Param("hospitalType") Integer hospitalType,
    		@Param("docId") Integer docId
    		);
    
    
    String tokeDeshboradQuery = "select * from ehm_token_register etr \n"
    		+ "where date(visit_date) = current_date\n"
    		+ "and TOKEN_TYPE = :tokenType\n"
//    		+ "and  etr.HOS_TYPE = :hosType\n"
			+ "AND COALESCE( etr.HOS_TYPE ,1) = CASE WHEN :hosType = 0 THEN COALESCE(etr.HOS_TYPE, 1) ELSE :hosType END\r\n"
    		+ "and action_token = false\n"
    		+ "and active = true\n"
    		+ "order by id";

    @Query(value = tokeDeshboradQuery, nativeQuery = true)
    List<TokenRegister> getTokenForDashBoard(
    		@Param("tokenType") Integer tokenType,
			@Param("hosType") Integer hosType

    		);
    
    
    String doctorTokenQuery = "select regi.* \n"
    		+ "from ehm_token_register regi, ehm_doctor_and_staff_information doctor, sya_app_user appuser\n"
    		+ "where 1=1\n"
    		+ "and appuser.id = :appUserId\n"
    		+ "and regi.HOS_TYPE in (301, :hosType)\n"
    		+ "and doctor.id = regi.refer_to_doctor_id \n"
    		+ "and appuser.id = doctor.app_user_id \n"
    		+ "and date(regi.visit_date) = current_date\n"
    		+ "and regi.action_token = false\n"
    		+ "and regi.active = true\n"
    		+ "order by regi.is_absence, regi.id";

    @Query(value = doctorTokenQuery, nativeQuery = true)
    List<TokenRegister> getTokenByDoctor(
    		@Param("appUserId") Integer appUserId,
    		@Param("hosType") Integer hosType
    		);
    
    String emergencyQuery = "select regi.* \n"
    		+ "from ehm_token_register regi\n"
    		+ "where 1=1\n"
    		+ "and regi.HOS_TYPE = :hosType\n"
    		+ "and date(regi.visit_date) = current_date\n"
    		+ "and regi.action_token = false\n"
    		+ "and regi.active = true\n"
    		+ "order by regi.id";

    @Query(value = emergencyQuery, nativeQuery = true)
    List<TokenRegister> getTokenEmergency(
    		@Param("hosType") Integer hosType
    		);


	String EmployeeSearchQuery = "select * \n" +
			"from ehm_token_register tr\n" +
			"where 1=1\n" +
			"and tr.PATIENT_ID = :patient";

	@Query(value = EmployeeSearchQuery, nativeQuery = true)
	List<TokenRegister> findByPatientId(
			@Param("patient") Integer patient
	);

	String EmployeeVisitDateSearchQuery = "select * \n"
			+ "from ehm_token_register\n"
			+ "where 1=1\n"
			+ "and PATIENT_ID = :patient\n"
			+ "and date(visit_date) = :visitDate\n"
			+ "and HOS_TYPE = :hosType\n"
			+ "and visit_type = :visitType";

	@Query(value = EmployeeVisitDateSearchQuery, nativeQuery = true)
	List<TokenRegister> findByPatientIdWithVisitDate(
			@Param("patient") Integer patient,
			@Param("visitDate") Date visitDate,
			@Param("hosType") Integer hosType,
			@Param("visitType") Integer visitType
	);
	
	
	String currentDayMedicineTokenQuery = "select * \n"
			+ "from EHM_TOKEN_REGISTER\n"
			+ "where 1 = 1 \n"
			+ "and date(visit_date) = current_date  \n"
			+ "and TOKEN_TYPE = 2 \n"
			+ "order by TOKEN_NUMBER";

    @Query(value = currentDayMedicineTokenQuery, nativeQuery = true)
    List<TokenRegister> findCurrentDayMedicineToken();

	String qryMonth = "select *\n" +
			"from ehm_token_register a\n" +
			"where 1=1\n" +
			"and a.PATIENT_ID = :patient\n"+
			"and to_char(a.visit_date, 'MM')  = :month";

    @Query(value = qryMonth, nativeQuery = true)
    List<TokenRegister> findCurrentMonth(
			@Param("patient") Integer patient,
			@Param("month") String month
	);

	String qryChoronic = "select a.*\n" +
			"from ehm_token_register a\n" +
			"where 1=1\n" +
			"and a.visit_type = 27\n" +
			"and a.PATIENT_ID = :patient\n" +
			"order by id desc\n" +
			"limit 1";

	@Query(value = qryChoronic, nativeQuery = true)
	List<TokenRegister> findChoronic(
			@Param("patient") Integer patient
	);

}


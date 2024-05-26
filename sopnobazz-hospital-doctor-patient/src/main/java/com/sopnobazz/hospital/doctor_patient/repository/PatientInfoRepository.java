package com.ibcsprimax.bof.employee_health.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientInfoRepository extends JpaRepository<PatientInfo, Integer>{
	List<PatientInfo> findByActive(boolean active);
	
	String qry = "select * from ehm_patient_info epi \r\n"
			+ "where 1=1 \r\n"
			+ "AND epi.patient_name ilike %:patientName%\r\n"
			+ "AND epi.patient_code ilike %:patientCode%\r\n"
			+ "AND epi.contact_no ilike %:contactNo%\r\n"
			+ "AND COALESCE(epi.patient_type_id,1) = CASE WHEN :patientType = 0 THEN COALESCE(epi.patient_type_id, 1) ELSE :patientType END\r\n"
			+ "AND ((cast(:fromDate as date) IS NULL OR cast(:toDate as date) IS NULL) OR epi.registration_date BETWEEN :fromDate AND :toDate)\r\n"
			+ "order by id desc\r\n";
		
           
	@Query(value = qry, nativeQuery = true)
    List<PatientInfo> getBySearchParam(
    		@Param("patientName") String patientName, 
    		@Param("patientCode") String patientCode, 
    		@Param("contactNo") String contactNo, 
    		@Param("fromDate") @Temporal Date fromDate,
    		@Param("toDate") @Temporal  Date toDate,
    		@Param("patientType") Integer patientType
    		);
	
	
	String searchQuery = "select * \n"
			+ "from ehm_patient_info pat\n"
			+ "where 1=1\n"
			+ "and concat(pat.patient_name, pat.patient_code) ilike %:searchValue% \n"
			+ "and pat.active is true\n"
			+ "order by pat.patient_name";

    @Query(value = searchQuery, nativeQuery = true)
    List<PatientInfo> findBySearchValue(
            @Param("searchValue") String searchValue
    );

	String patientSearchQuery = "select p.* \n"
			+ "from EHM_PATIENT_INFO p\n"
			+ "where 1=1\n"
			+ "AND p.patient_type_id in (1,3)";


	@Query(value = patientSearchQuery, nativeQuery = true)
	List<PatientInfo> findByPatient();

	String EmployeeSearchQuery = "select * \n" +
			"from ehm_patient_info pat\n" +
			"where 1=1\n" +
			"and pat.employee_id = :employee";


	@Query(value = EmployeeSearchQuery, nativeQuery = true)
	List<PatientInfo> findByEmployeeId(
			@Param("employee") Integer employee
	);

	String PatientMiscellaneousQuery = "select * \n" +
			"from ehm_patient_info pat\n" +
			"where 1=1\n" +
			"and patient_type_id = 3\n" +
			"and emp_code = :patientMiscellaneous";

	@Query(value = PatientMiscellaneousQuery, nativeQuery = true)
			List<PatientInfo> findByPatientMiscellaneous(
			@Param("patientMiscellaneous") String patientMiscellaneous
	);
	
	String EmployeeCodeSearchQuery = "select * \n" +
			"from ehm_patient_info pat\n" +
			"where 1=1\n" +
			"and lower(pat.EMP_CODE)  = lower(:employeeCode)";


	@Query(value = EmployeeCodeSearchQuery, nativeQuery = true)
	List<PatientInfo> findByEmployeeCode(
			@Param("employeeCode") String employeeCode
	);
	
	


	String countQuery = "select count(id)\n" +
			"from ehm_patient_info epi\n" +
			"where patient_type_id = 3";

	@Query(value = countQuery, nativeQuery = true)
	int countPatient(

	);

	String countRelativeQuery = "select count(id)\n" +
			"from ehm_patient_info epi\n" +
			"where 1=1\n" +
			"and patient_type_id = 2\n" +
			"and patient_id = :patientId";

	@Query(value = countRelativeQuery, nativeQuery = true)
	int countRelativePatient(
			@Param("patientId") Integer patientId
	);

	String searchQueryRelative = "select * \n"
			+ "from ehm_patient_info pat\n"
			+ "where 1=1\n"
			+ "and concat(pat.patient_name, pat.patient_code) ilike %:searchValue% \n"
			+ "and PATIENT_TYPE_ID in (1, 3) \n"
			+ "order by pat.patient_name";

	@Query(value = searchQueryRelative, nativeQuery = true)
	List<PatientInfo> findByRelativeSearchValue(
			@Param("searchValue") String searchValue
	);

	String duplicateQueryCheck = "select a.* from EHM_PATIENT_INFO a\n"
			+ "where 1 = 1\n"
			+ "and a.PATIENT_ID = :patientId\n"
			+ "and a.relative_id = :relativeId \n";

	@Query(value = duplicateQueryCheck, nativeQuery = true)
	List<PatientInfo> duplicateCheckRepo(
			@Param("patientId") Integer patientId,
			@Param("relativeId") Integer relativeId
	);

	Page<PatientInfo> findAllByPatientTypeOrderByIdDesc(int hosType, Pageable pageable);

}

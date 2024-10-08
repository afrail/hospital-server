package com.sopnobazz.demo.doctor_patient.repository;

import java.util.Date;
import java.util.List;

import com.sopnobazz.demo.doctor_patient.entity.PatientPrescriptionMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientPrescriptionMasterRepository extends JpaRepository<PatientPrescriptionMaster,Integer>{
	
	List<PatientPrescriptionMaster> findByActive(boolean active);
	
	List<PatientPrescriptionMaster> findByPrescriptionNo(String prescriptionNo);
	Page<PatientPrescriptionMaster> findByTemplateIsIsFalse(Pageable pageable);

	String countQuery = "select count(id) from EHM_PAT_PRES_MASTER etr\n"
    		+ "where date(PRESCRIPTION_DATE) = :paramDate";

    @Query(value = countQuery, nativeQuery = true)
    int countData(
    		@Param("paramDate") Date paramDate
    		);
	
	String searchQuery = "select master.*\n"
			+ "FROM EHM_PAT_PRES_MASTER master\n"
			+ "WHERE 1 = 1\n"
			+ "AND COALESCE(master.DOCTOR_INFO_ID, 1) = CASE WHEN :doctorId = 0 THEN COALESCE(master.DOCTOR_INFO_ID, 1) ELSE :doctorId END\n"
			+ "and ((cast(:fromDate as date) IS NULL OR cast(:toDate as date) IS NULL) OR date(master.PRESCRIPTION_DATE) BETWEEN :fromDate AND :toDate)\n"
		    + "AND IS_TEMPLATE = false\n"
			+ "order by id desc";
    @Query(value = searchQuery, nativeQuery = true)
    List<PatientPrescriptionMaster> getBySearchParam(
    		@Param("doctorId") Integer doctorId,
            @Param("fromDate") @Temporal Date  fromDate,
            @Param("toDate") @Temporal Date toDate
    );
    
	String EmployeeSearchQuery = "select * \n" +
			"from EHM_PAT_PRES_MASTER pat\n" +
			"where 1=1\n" +
			"and pat.PATIENT_INFO_ID = :patientId\n" +
			"order by id desc";


	@Query(value = EmployeeSearchQuery, nativeQuery = true)
	List<PatientPrescriptionMaster> findByPatientId(
			@Param("patientId") Integer patientId
	);

    List<PatientPrescriptionMaster> findByTemplateIsIsTrue();
    
    
    String chronicPatPrescriptionQuery = "select * \n"
    		+ "from EHM_PAT_PRES_MASTER pat\n"
    		+ "where 1=1\n"
    		+ "and pat.PATIENT_INFO_ID = :patientId\n"
    		+ "and id in (\n"
    		+ "			select distinct pat_pres_master_id \n"
    		+ "			from ehm_pat_pres_treatment eppt\n"
    		+ "			where is_continue = true\n"
    		+ "			)\n"
    		+ "order by id desc\n"
    		+ "limit 1";

	@Query(value = chronicPatPrescriptionQuery, nativeQuery = true)
	List<PatientPrescriptionMaster> findChronicPatientLastPrescription(
			@Param("patientId") Integer patientId
	);
    
}

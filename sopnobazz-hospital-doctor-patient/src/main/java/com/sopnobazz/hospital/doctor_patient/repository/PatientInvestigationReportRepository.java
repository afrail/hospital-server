/**
 * 
 */
package com.sopnobazz.hospital.doctor_patient.repository;

import java.util.Date;
import java.util.List;

import com.sopnobazz.hospital.doctor_patient.entity.PatientInvestigationReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




public interface PatientInvestigationReportRepository extends JpaRepository<PatientInvestigationReport, Integer> {

	List<PatientInvestigationReport> findByActive(boolean b);
	
	String detailsQuery = "SELECT *\r\n"
			+ "	FROM EHM_PATIENT_ENVESTIGATION_REPORT dbi \r\n"
			+ " WHERE 1 = 1\r\n"
			+ " AND DATE(dbi.INV_DATE) BETWEEN DATE(:fromDate) AND DATE(:toDate)\r\n"
//			+ " AND dbi.REFERENCE_NO  ILIKE %:referenceNo%\r\n"
			+ " ORDER BY dbi.ID desc";
    
    @Query(value = detailsQuery, nativeQuery = true)
    List<PatientInvestigationReport> search(
//    		@Param("referenceNo") String referenceNo
    		@Param("fromDate") Date fromDate, 
    		@Param("toDate") Date toDate
    		);

}

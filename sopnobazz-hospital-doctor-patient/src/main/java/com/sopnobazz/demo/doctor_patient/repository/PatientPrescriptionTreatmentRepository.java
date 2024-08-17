package com.sopnobazz.demo.doctor_patient.repository;

import com.sopnobazz.demo.doctor_patient.entity.PatientPrescriptionTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Since Dec 26, 2021
 * @Author Md. Chabed Alam - 601
 * @Project ibcs-bof-erp
 * @version   2.0.0
 */

@Repository
public interface PatientPrescriptionTreatmentRepository extends JpaRepository<PatientPrescriptionTreatment, Integer> {
    
	List<PatientPrescriptionTreatment> findByActive(boolean active);
    
    List<PatientPrescriptionTreatment> findByPrescriptionMasterIdOrderByIdAsc(Integer prescriptionMasterId);

    long deleteByPrescriptionMasterId(Integer prescriptionMasterId);
    
    String findByIdQuery = "select * \n"
    		+ "from EHM_PAT_PRES_TREATMENT\n"
    		+ "where 1=1\n"
    		+ "and id = :mainId";

    @Query(value = findByIdQuery, nativeQuery = true)
    PatientPrescriptionTreatment findByIdMainId(
			@Param("mainId") Integer mainId
    );
    
    
    String continueMedicineQuery = "select distinct medicine_master_id medicine_master_id,\n" +
			"medi.id, medi.active, medi.entry_app_user_code, medi.entry_date, medi.entry_user, medi.update_app_user_code,\n" +
			"medi.update_date, medi.update_user,\n" +
			"is_continue,delivery_qty,dose,duration,instruction,qty,dose_id,instruction_id,pat_pres_master_id,dwmy,serial_no\n" +
			"from ehm_pat_pres_treatment medi, ehm_pat_pres_master master\n" +
			"where 1=1\n" +
			"and medi.pat_pres_master_id = master.id\n" +
			"and is_continue = true\n" +
			"and master.patient_info_id = :patId";

    @Query(value = continueMedicineQuery, nativeQuery = true)
    List<PatientPrescriptionTreatment> findContinueMedicine(
			@Param("patId") Integer patId
    );
}


package com.sopnobazz.demo.doctor_patient.repository;


import java.util.List;
import com.sopnobazz.demo.doctor_patient.entity.PatientInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientInfoRepository extends JpaRepository<PatientInfo, Integer>{
	List<PatientInfo> findByActive(boolean active);


	String qry= "select * \n" +
			"from ehm_patient_info pat\n" +
			"where 1=1\n" +
			"and lower(pat.CONTACT_NO)  = lower(:phone)";
	@Query(value = qry, nativeQuery = true)
	PatientInfo findByContactNo(
			@Param("phone") String phone
	);

	Page<PatientInfo> findAll(Pageable pageable);

}
